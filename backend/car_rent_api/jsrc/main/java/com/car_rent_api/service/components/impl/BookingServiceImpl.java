package com.car_rent_api.service.components.impl;

import com.car_rent_api.config.TableKeys;
import com.car_rent_api.exception.DateTimeException;
import com.car_rent_api.exception.ExistenceException;
import com.car_rent_api.exception.OperationFailedException;
import com.car_rent_api.persistence.dao.components.AuthDao;
import com.car_rent_api.persistence.dao.components.BookingDao;
import com.car_rent_api.persistence.dao.components.CarDao;
import com.car_rent_api.persistence.dao.components.UserDao;
import com.car_rent_api.persistence.models.dto.booking.BookCarRequest;
import com.car_rent_api.persistence.models.dto.booking.BookCarResponse;
import com.car_rent_api.persistence.models.dto.booking.BookingInfo;
import com.car_rent_api.persistence.models.dto.booking.BookingsResponse;
import com.car_rent_api.persistence.models.entity.Booking;
import com.car_rent_api.persistence.models.entity.Car;
import com.car_rent_api.persistence.models.entity.User;
import com.car_rent_api.persistence.models.entity.types.BookingStatus;
import com.car_rent_api.persistence.models.entity.types.CarStatus;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import com.car_rent_api.persistence.pagination.api.PaginationRequest;
import com.car_rent_api.persistence.pagination.api.TableRequest;
import com.car_rent_api.persistence.pagination.api.SpecificationRequest;
import com.car_rent_api.persistence.pagination.type.JoinType;
import com.car_rent_api.persistence.pagination.type.ValueType;
import com.car_rent_api.service.components.BookingService;
import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.StringDateConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private final AuthDao authDao;
    private final UserDao userDao;
    private final CarDao carDao;

    public BookingServiceImpl(BookingDao bookingDao, AuthDao authDao, UserDao userDao, CarDao carDao) {
        this.bookingDao = bookingDao;
        this.authDao = authDao;
        this.userDao = userDao;
        this.carDao = carDao;
    }

    @Override
    public BookCarResponse create(String accessToken, BookCarRequest bookCarRequest) {
        LogPrinter.warn("[BookingService | Create] Creating booking...");
        LogPrinter.warn("[BookingService | Create] Checking car existence...");
        checkCarExistence(bookCarRequest.getCarId());

        LogPrinter.warn("[BookingService | Create] Checking dates format...");
        String pickupDateTime = bookCarRequest.getPickupDateTime().replace(" ", "T");
        String dropOffDateTime = bookCarRequest.getDropOffDateTime().replace(" ", "T");
        checkDates(pickupDateTime, dropOffDateTime);
        checkBookedDatesAreISO8601Format(pickupDateTime, dropOffDateTime);

        LogPrinter.warn("[BookingService | Create] Checking for locations and overlapping dates...");
        Map<String, String> filterParams = Map.of(
                "id", bookCarRequest.getCarId(),
                "pickupLocationId", bookCarRequest.getPickupLocationId(),
                "dropOffLocationId", bookCarRequest.getDropOffLocationId(),
                "pickupDateTime", pickupDateTime,
                "dropOffDateTime", dropOffDateTime
        );
        areLocationsAndDatesInRange(filterParams);

        LogPrinter.warn("[BookingService | Create] Checking token...");
        checkAccessToken(accessToken);
        String subId = authDao.getSubFromJwt(accessToken);

        LogPrinter.warn("[BookingService | Create] Getting user from token sub id...");
        User user = userDao.findById(subId);

        LogPrinter.warn("[BookingService | Create] Checking client...");
        String clientId = bookCarRequest.getClientId();
        checkUserExistence(clientId);

        LogPrinter.warn("[BookingService | Create] Checking user permissions and generating booking status...");
        BookingStatus bookingStatus = getBookingStatus(user, clientId);

        LogPrinter.warn("[BookingService | Create] Generating booking...");
        Booking booking = toBooking(bookCarRequest, bookingStatus);

        LogPrinter.warn("[BookingService | Create] Commencing booking creation...");
        waitAndCheckDateTimes(filterParams);
        bookingDao.put(booking);

        LogPrinter.warn("[BookingService | Create] Updating book dates for car with id {}", booking.getCarId());
        updateCarBookedDays(bookCarRequest);

        LogPrinter.warn("[BookingService | Create] Booking {} was successfully created", booking.getSkId());

        return BookCarResponse.builder()
                .constructMessage(
                        carDao.findById(booking.getCarId()).getModel(),
                        booking.getPickupDateTime(),
                        booking.getDropOffDateTime(),
                        booking.getLockedFrom(),
                        booking.getOrderDetails()
                )
                .build();
    }

    @Override
    public BookingsResponse findByAccessTokenAndClientId(String accessToken, String clientId) {
        checkAccessToken(accessToken);
        checkUserExistence(clientId);
        String subId = authDao.getSubFromJwt(accessToken);
        User user = userDao.findById(subId);
        checkUserExistence(clientId);
        boolean isSelfId = user.getSkId().replace(TableKeys.USER_SK_PREFIX, "").equals(clientId);
        UserRole userRole = user.getRole();

        if (userRole == UserRole.ADMIN) {
            LogPrinter.warn("[BookingService | Create] Admin attempted to book car for himself");
            throw new OperationFailedException("Prohibited by role");
        }

        if ((userRole == UserRole.SUPPORT_AGENT && !isSelfId) || (userRole == UserRole.CLIENT && isSelfId)) {
            TableRequest tableRequest = TableRequest.builder()
                    .pagination( PaginationRequest.builder()
                            .defaultSort(TableKeys.BOOKING_CREATED_AT_IDX)
                            .defaultDirection(false)
                            .build())
                    .specification(SpecificationRequest.builder()
                            .equalTo(ValueType.STRING, "BOOKING#CLIENT_ID", clientId)
                            .build(JoinType.AND))
                    .build();

            List<Booking> bookingInfos = bookingDao.findByTableRequestIndexed(tableRequest).getItems();

            return BookingsResponse.builder().content(bookingInfos.stream().map(toBookingInfo()).toList()).build();
        } else {
            LogPrinter.warn("[BookingService | Create] User with role {} attempted to book car, but lacked on " +
                    "permissions", userRole.getName());
            throw new OperationFailedException("Prohibited by role");
        }
    }

    private Function<Booking, BookingInfo> toBookingInfo() {
        return b -> BookingInfo.builder()
                .bookingId(b.getSkId().replace(TableKeys.BOOKING_SK_PREFIX, ""))
                .bookingStatus(b.getStatus().getName())
                .carModel(carDao.findById(b.getCarId()).getModel())
                .carImageUrl(carDao.findById(b.getCarId()).getImageUrl())
                .orderDetails(b.getOrderDetails())
                .build();
    }

    private void updateCarBookedDays(BookCarRequest bookCarRequest) {
        Car car = carDao.findById(bookCarRequest.getCarId())
                .toBuilder()
                .bookedDays(StringDateConverter.generateGermanDatesRange(
                        bookCarRequest.getPickupDateTime().replace(" ", "T"),
                        bookCarRequest.getDropOffDateTime().replace(" ", "T")))
                .build();
        carDao.put(car);
    }

    private void waitAndCheckDateTimes(Map<String, String> filterParams) {
        try {
            Thread.sleep(3 * 1000);
            areLocationsAndDatesInRange(filterParams);
        } catch (InterruptedException e) {
            LogPrinter.error("[BookingService | Create] Booking on this time is already exists");
            throw new OperationFailedException("Booking on this time is already exists");
        }
    }

    private Booking toBooking(BookCarRequest bookCarRequest, BookingStatus bookingStatus) {
        DateTimeFormatter createdAtFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        String createdAt = LocalDateTime.now().format(createdAtFormatter);
        LogPrinter.info("[BookingService | Create] Created at date {}", createdAt);

        String lockedFrom = StringDateConverter.toISO8601DateTime(
                LocalDateTime.parse(bookCarRequest.getPickupDateTime().replace(" ", "T")).minusHours(12)
        );
        LogPrinter.info("[BookingService | Create] Locked from date {}", lockedFrom);

        return Booking.builder()
                .pkId()
                .skId(UUID.randomUUID().toString())
                .orderDetails("#" + (bookingDao.getTotalCount() + 1) + " (" + createdAt + ")")
                .status(bookingStatus)
                .clientId(bookCarRequest.getClientId())
                .carId(bookCarRequest.getCarId())
                .createdAt(createdAt)
                .lockedFrom(lockedFrom)
                .pickupDateTime(bookCarRequest.getPickupDateTime())
                .dropOffDateTime(bookCarRequest.getDropOffDateTime())
                .pickupLocationId(bookCarRequest.getPickupLocationId())
                .dropOffLocationId(bookCarRequest.getDropOffLocationId())
                .build();
    }

    private void checkCarExistence(String carId) {
        if (carId == null || !carDao.isExistsById(carId) ||
                carDao.findById(carId).getStatus() == CarStatus.UNAVAILABLE) {
            LogPrinter.error("Car with id {}", TableKeys.CAR_SK_PREFIX + carId + " does not exist");
            throw new ExistenceException("Car wasn't found or unavailable");
        }
    }

    private void checkDates(String pickupDateTime, String dropOffDateTime) {
        checkBookedDatesIsNull(pickupDateTime, dropOffDateTime);
        pickupDateTime = pickupDateTime.replace(" ", "T");
        dropOffDateTime = dropOffDateTime.replace(" ", "T");
        checkBookedDatesIsNull(pickupDateTime, dropOffDateTime);
    }

    private void areLocationsAndDatesInRange(Map<String, String> params) {
        params = params == null ? Map.of() : params;
        List<String> datesRange = StringDateConverter.generateGermanDatesRange(params.get("pickupDateTime"),
                params.get("dropOffDateTime"));

        TableRequest carTableRequest = TableRequest.builder()
                .specification(SpecificationRequest.builder()
                        .equalTo(ValueType.STRING, "CAR#PICKUP_LOCATION_ID", params.get("locationId"))
                        .equalTo(ValueType.STRING, "CAR#DROPOFF_LOCATIONS_IDS", params.get("dropOffLocationId"))
                        .notInRange(ValueType.STRING, "CAR#BOOKED_DAYS", datesRange)
                        .build(JoinType.AND))
                .build();

        if (!carDao.isBookedDatesAreFree(params.get("id"), carTableRequest)) {
            LogPrinter.error("Requested book dates are overlapping existing ones");
            throw new OperationFailedException("No locations found or dates are unavailable");
        }
    }

    private void checkAccessToken(String accessToken) {
        if (accessToken == null || authDao.getSubFromJwt(accessToken) == null) {
            LogPrinter.error("Access Token {} is invalid", accessToken);
            throw new ExistenceException("Access Token is invalid");
        }
    }

    private void checkUserExistence(String clientId) {
        if (clientId == null || !userDao.isExistsById(clientId)) {
            LogPrinter.error("User with id {} does not exist", TableKeys.USER_SK_PREFIX + clientId);
            throw new ExistenceException("User wasn't found");
        }
    }

    private void checkBookedDatesIsNull(String pickupDateTime, String dropOffDateTime) {
        if (pickupDateTime == null || dropOffDateTime == null) {
            LogPrinter.error("One of the dates is null pickupDateTime {}, dropOffDateTime {}",
                    pickupDateTime, dropOffDateTime);
            throw new DateTimeException("Dates are in incorrect format, try [yyyy-MM-dd HH:mm]");
        }
    }

    //TODO: Method isClientIdBelongsToSupportAgent(){}

    private BookingStatus getBookingStatus(User user, String clientId) {
        boolean isSelfId = user.getSkId().replace(TableKeys.USER_SK_PREFIX, "").equals(clientId);
        UserRole userRole = user.getRole();

        if (userRole == UserRole.ADMIN) {
            LogPrinter.warn("[BookingService | Create] Admin attempted to book car for himself");
            throw new OperationFailedException("You can't book at this time");
        }

        if ((userRole == UserRole.SUPPORT_AGENT && !isSelfId) || (userRole == UserRole.CLIENT && isSelfId)) {
            return userRole == UserRole.SUPPORT_AGENT ?
                    BookingStatus.RESERVED_BY_SUPPORT_AGENT : BookingStatus.RESERVED;
        } else {
            LogPrinter.warn("[BookingService | Create] User with role {} attempted to book car, but lacked on " +
                            "permissions", userRole.getName());
            throw new OperationFailedException("Prohibited by role");
        }
    }

    private void checkBookedDatesAreISO8601Format(String pickupDateTime, String dropOffDateTime) {
        if (!StringDateConverter.isISO8601DateTime(pickupDateTime) ||
                !StringDateConverter.isISO8601DateTime(dropOffDateTime)) {
            LogPrinter.error("One of the dates is in incorrect format pickupDateTime {}, dropOffDateTime {}",
                    pickupDateTime, dropOffDateTime);
            throw new DateTimeException("Dates are in incorrect format, try [yyyy-MM-dd HH:mm]");
        }
    }
}
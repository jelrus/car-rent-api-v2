package com.car_rent_api.service.components.impl;

import com.car_rent_api.config.TableKeys;
import com.car_rent_api.exception.DateTimeException;
import com.car_rent_api.exception.ExistenceException;
import com.car_rent_api.exception.OperationFailedException;
import com.car_rent_api.persistence.dao.components.*;
import com.car_rent_api.persistence.models.dto.booking.*;
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
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private final AuthDao authDao;
    private final UserDao userDao;
    private final CarDao carDao;
    private final LocationDao locationDao;

    public BookingServiceImpl(BookingDao bookingDao, AuthDao authDao, UserDao userDao, CarDao carDao,
                              LocationDao locationDao) {
        this.bookingDao = bookingDao;
        this.authDao = authDao;
        this.userDao = userDao;
        this.carDao = carDao;
        this.locationDao = locationDao;
    }

    @Override
    public BookCarResponse create(String accessToken, BookCarRequest createRequest) {
        checkCar(createRequest.getCarId());
        Car car = carDao.findById(createRequest.getCarId());
        CarStatus statusBeforeCreation = car.getStatus();
        car.toBuilder().status(CarStatus.UNAVAILABLE).build();
        carDao.put(car);

        try {
            String pickupDateTime = createRequest.getPickupDateTime();
            String dropOffDateTime = createRequest.getDropOffDateTime();
            createRequest.toBuilder().pickupDateTime(pickupDateTime).dropOffDateTime(dropOffDateTime).build();
            checkInputDates(createRequest.getPickupDateTime(), createRequest.getDropOffDateTime());

            List<String> bookingDates =
                    generateBookingDates(createRequest.getPickupDateTime(), createRequest.getDropOffDateTime());

            checkForCompatibleLocations(car, createRequest.getPickupLocationId(), createRequest.getDropOffLocationId());
            checkForCompatibleDates(car, bookingDates);

            Booking booking = toCreateBooking(userDao.findById(authDao.getSubFromJwt(accessToken)), createRequest);
            waitAndRecheck(car, bookingDates);

            car.getBookedDays().addAll(bookingDates);
            car.toBuilder().status(CarStatus.BOOKED).build();
            carDao.put(car);

            return toBookingCreateResponse(bookingDao.put(booking));
        } catch (OperationFailedException | ExistenceException | DateTimeException e) {
            car.toBuilder().status(statusBeforeCreation).build();
            carDao.put(car);
            throw new OperationFailedException(e.getMessage());
        }
    }

    @Override
    public BookCarResponse edit(String bookingId, BookCarEditRequest editRequest) {
        checkBooking(bookingId);

        String pickupDateTime = editRequest.getPickupDateTime();
        String dropOffDateTime = editRequest.getDropOffDateTime();
        editRequest.toBuilder().pickupDateTime(pickupDateTime).dropOffDateTime(dropOffDateTime).build();
        checkInputDates(editRequest.getPickupDateTime(), editRequest.getDropOffDateTime());

        Booking booking = bookingDao.findById(bookingId);
        isReservedBookingStatus(booking.getStatus());

        checkCar(booking.getCarId());
        Car car = carDao.findById(booking.getCarId());
        CarStatus statusBeforeEdit = car.getStatus();
        car.toBuilder().status(CarStatus.UNAVAILABLE).build();
        carDao.put(car);

        try {
            isReservedBookingStatus(booking.getStatus());
            checkLockedDate(booking.getLockedFrom());
            checkInputDates(editRequest.getPickupDateTime(), editRequest.getDropOffDateTime());
            checkForCompatibleLocations(car, editRequest.getPickupLocationId(), editRequest.getDropOffLocationId());

            List<String> current = generateBookingDates(booking.getPickupDateTime(), booking.getDropOffDateTime());
            car.getBookedDays().removeAll(current);

            List<String> toUpdate =
                    generateBookingDates(editRequest.getPickupDateTime(), editRequest.getDropOffDateTime());
            checkForCompatibleDates(car, toUpdate);
            car.getBookedDays().addAll(toUpdate);
            car.toBuilder().status(CarStatus.BOOKED).build();

            booking = toEditBooking(booking, editRequest);
            carDao.put(car);
        } catch (OperationFailedException | ExistenceException | DateTimeException e) {
            car.toBuilder().status(statusBeforeEdit).build();
            carDao.put(car);
            throw new OperationFailedException(e.getMessage());
        }

        return toBookingEditResponse(bookingDao.put(booking));
    }

    @Override
    public BookCarResponse cancel(String bookingId) {
        checkBooking(bookingId);
        Booking booking = bookingDao.findById(bookingId);

        isReservedBookingStatus(booking.getStatus());
        checkLockedDate(booking.getLockedFrom());

        booking.toBuilder().status(BookingStatus.CANCELLED).build();

        Car car = carDao.findById(booking.getCarId());

        List<String> bookedDates = generateBookingDates(booking.getPickupDateTime(), booking.getDropOffDateTime());
        car.getBookedDays().removeAll(bookedDates);

        carDao.put(car);
        bookingDao.put(booking);

        return toBookingCancelResponse(booking);
    }

    public BookCarResponse onServiceStarted(String accessToken, String bookingId) {
        checkBooking(bookingId);
        Booking booking = bookingDao.findById(bookingId);

        checkServiceTime(booking.getPickupDateTime());
        checkForCompatibleSupportAgent(accessToken, booking.getPickupLocationId());
        isReservedBookingStatus(booking.getStatus());

        Car car = carDao.findById(booking.getCarId());
        Integer mileage = car.getMileageTotal() == null ? 0 : car.getMileageTotal();
        car.toBuilder().mileageTotal(mileage).build();

        booking.toBuilder().status(BookingStatus.SERVICE_STARTED).carMileageStart(car.getMileageTotal()).build();

        carDao.put(car);
        bookingDao.put(booking);

        return toBookingServiceStartedResponse(booking);
    }

    public BookCarResponse onServiceProvided(String accessToken, String bookingId,
                                             BookCarServiceProvidedRequest serviceProvidedRequest) {
        LogPrinter.info("Car mileage end {}", serviceProvidedRequest.getMileage());
        checkBooking(bookingId);
        Booking booking = bookingDao.findById(bookingId);

        checkServiceTime(booking.getDropOffDateTime());
        checkForCompatibleSupportAgent(accessToken, booking.getDropOffLocationId());
        isStartedBookingStatus(booking.getStatus());

        booking.toBuilder().status(BookingStatus.SERVICE_PROVIDED)
                .carMileageEnd(serviceProvidedRequest.getMileage())
                .supportAgentId(locationDao.findById(booking.getDropOffLocationId()).getSupportAgentId())
                .build();

        Car car = carDao.findById(booking.getCarId());

        List<String> datesInUse = generateBookingDates(booking.getPickupDateTime(), booking.getDropOffDateTime());
        car.getBookedDays().removeAll(datesInUse);
        CarStatus updatedStatus = reevaluateCarStatus(car);

        checkEndMileage(car.getMileageTotal(), serviceProvidedRequest.getMileage());
        car.toBuilder().status(updatedStatus).mileageTotal(serviceProvidedRequest.getMileage()).build();

        carDao.put(car);
        bookingDao.put(booking);

        return toBookingServiceProvidedResponse(booking);
    }

    public void onBookingFinished(String bookingId) {
        //if bookingStatus != BookingStatus.SERVICE_PROVIDED => throw new OperationFailedException("You can't do it right now")
        //change booking status => bookingStatus = BookingStatus.BOOKING_FINISHED
        //bookingService.put(booking)
    }

    public BookingsResponse findByClientId(String clientId) {
        TableRequest tableRequest = TableRequest.builder()
                .pagination(PaginationRequest.builder()
                        .defaultSort(TableKeys.BOOKING_CREATED_AT_IDX)
                        .defaultDirection(false)
                        .build())
                .specification(SpecificationRequest.builder()
                        .equalTo(ValueType.STRING, "BOOKING#CLIENT_ID", clientId)
                        .build(JoinType.AND))
                .build();

        List<Booking> bookingInfos = bookingDao.findByTableRequestIndexed(tableRequest).getItems();

        return BookingsResponse.builder().content(bookingInfos.stream().map(toBookingInfo()).toList()).build();
    }

    private void checkBooking(String bookingId) {
        if (!bookingDao.isExistsById(bookingId)) {
            throw new ExistenceException("Booking wasn't found or unavailable");
        }
    }

    private void isReservedBookingStatus(BookingStatus bookingStatus) {
        if (bookingStatus != BookingStatus.RESERVED && bookingStatus != BookingStatus.RESERVED_BY_SUPPORT_AGENT) {
            throw new OperationFailedException("Booking can't be edited on this stage");
        }
    }

    private void isStartedBookingStatus(BookingStatus bookingStatus) {
        if (bookingStatus != BookingStatus.SERVICE_STARTED) {
            throw new OperationFailedException("Booking can't be edited on this stage");
        }
    }

    private void checkLockedDate(String lockedFrom) {
        if (StringDateConverter.isCurrentTimeAfterOrEqualDateTime(lockedFrom)) {
            throw new OperationFailedException("Booking is already locked and can't be edited");
        }
    }

    private void checkCar(String carId) {
        if (!carDao.isExistsById(carId) || carDao.findById(carId).getStatus() == CarStatus.UNAVAILABLE) {
            LogPrinter.error("Car with id {}", TableKeys.CAR_SK_PREFIX + carId + " does not exist");
            throw new ExistenceException("Car wasn't found or unavailable");
        }
    }

    private void checkInputDates(String pickUpDateTime, String dropOffDateTime) {
        LogPrinter.info("pickupDateTime {}, dropOffDateTime {}", pickUpDateTime, dropOffDateTime);
        if (!StringDateConverter.isRangeCandidates(pickUpDateTime, dropOffDateTime) ||
                StringDateConverter.isSourceTimeAfterTargetTime(pickUpDateTime, dropOffDateTime) ||
                StringDateConverter.isTargetTimeBeforeCurrentTime(pickUpDateTime) ||
                StringDateConverter.isTargetTimeBeforeCurrentTime(dropOffDateTime)
            ) {
            throw new DateTimeException("Dates are in incorrect format, should be in [yyyy-MM-dd HH:mm] format," +
                    "have incorrect order (pick up date time after drop off date time), or you are trying to " +
                    "book date time that already passed (pick up date time or drop off date time is after current " +
                    "time). Please, check your input and try again.");
        }
    }

    private void checkServiceTime(String targetTime) {
        LogPrinter.info("PickupDateTime {}", targetTime);
        if (!StringDateConverter.isCurrentTimeAfterOrEqualDateTime(targetTime)) {
            throw new DateTimeException("Service cannot be set as started or provided. \n" +
                    "Pick up or drop off date time [" + targetTime + "] \n" +
                    "is before current time [" +
                    StringDateConverter.toISO8601DateTime(LocalDateTime.now(ZoneId.of("Europe/Kiev"))) + "]");
        }
    }

    private void checkForCompatibleDates(Car car, List<String> datesRange) {
        datesRange.forEach(d -> {
            if (car.getBookedDays().contains(d)) {
                throw new OperationFailedException("Booking failed. Car is already booked on these days.");
            }
        });
    }

    private void checkForCompatibleLocations(Car car, String pickUpLocationId, String dropOffLocationId) {
        boolean locationsAreIncompatible =
                !locationDao.isExistById(pickUpLocationId) ||
                !locationDao.isExistById(dropOffLocationId) ||
                !Objects.equals(car.getPickupLocationId(), pickUpLocationId) ||
                !car.getDropOffLocationsIds().contains(dropOffLocationId);

        if (locationsAreIncompatible) {
            throw new OperationFailedException("Booking failed. Locations are incompatible with this car.");
        }
    }

    private void checkForCompatibleSupportAgent(String accessToken, String locationId) {
        User user = userDao.findById(authDao.getSubFromJwt(accessToken));
        String userId = user.getSkId().replace(TableKeys.USER_SK_PREFIX, "");
        String supportAgentId = locationDao.findById(locationId).getSupportAgentId();
        boolean isNotTargetSupportAgent =
                user.getRole() != UserRole.SUPPORT_AGENT || !Objects.equals(userId, supportAgentId);

        if (isNotTargetSupportAgent) {
            throw new OperationFailedException("You don't have permissions to perform operation in this location." +
                    "Location: " + locationId + " \n" +
                    "UserRole: " + user.getRole() + " \n" +
                    "UserId: " + userId + " \n" +
                    "SupportAgentId: " + supportAgentId + " \n" +
                    "Is not target support agent: " + isNotTargetSupportAgent);
        }
    }

    private void checkEndMileage(Integer currentMileage, Integer inputMileage) {
        if (inputMileage < currentMileage) {
            throw new OperationFailedException("Input mileage cannot be less than the current mileage");
        }
    }

    private List<String> generateBookingDates(String pickupDateTime, String dropOffDateTime) {
        return StringDateConverter.generateGermanDatesRange(pickupDateTime, dropOffDateTime);
    }

    private BookingStatus getStatusFromUserRole(User authUser) {
        return authUser.getRole() == UserRole.SUPPORT_AGENT
                ? BookingStatus.RESERVED_BY_SUPPORT_AGENT
                : BookingStatus.RESERVED;
    }

    private CarStatus reevaluateCarStatus(Car car) {
        return car.getBookedDays().isEmpty() ? CarStatus.AVAILABLE : CarStatus.BOOKED;
    }

    private void waitAndRecheck(Car car, List<String> bookingDates) {
        try {
            Thread.sleep(3 * 1000);
            checkForCompatibleDates(car, bookingDates);
        } catch (InterruptedException e) {
            throw new OperationFailedException("Booking process was interrupted.");
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

    private BookCarResponse toBookingCreateResponse(Booking booking) {
        return BookCarResponse.builder()
                .constructCreateMessage(carDao.findById(booking.getCarId()).getModel(), booking)
                .build();
    }

    private BookCarResponse toBookingEditResponse(Booking booking) {
        return BookCarResponse.builder()
                .constructEditMessage(carDao.findById(booking.getCarId()).getModel(), booking)
                .build();
    }

    private BookCarResponse toBookingCancelResponse(Booking booking) {
        return BookCarResponse.builder()
                .constructCancelMessage(carDao.findById(booking.getCarId()).getModel(), booking)
                .build();
    }

    private BookCarResponse toBookingServiceStartedResponse(Booking booking) {
        return BookCarResponse.builder()
                .constructServiceStartedMessage(carDao.findById(booking.getCarId()).getModel(), booking)
                .build();
    }

    private BookCarResponse toBookingServiceProvidedResponse(Booking booking) {
        return BookCarResponse.builder()
                .constructServiceProvidedMessage(carDao.findById(booking.getCarId()).getModel(), booking)
                .build();
    }

    private Booking toCreateBooking(User authUser, BookCarRequest bookCarRequest) {
        String createdAt = StringDateConverter.fromLocalDateTimeToBookingCreatedAt();
        String lockedFrom = StringDateConverter.getLockedDateTime(bookCarRequest.getPickupDateTime());
        LogPrinter.info(authUser.getUsername());

        return Booking.builder()
                .pkId()
                .skId(UUID.randomUUID().toString())
                .orderDetails("#" + (bookingDao.getTotalCount() + 1) + " (" + createdAt + ")")
                .status(getStatusFromUserRole(authUser))
                .madeBy(authUser.getUsername() + " (" + authUser.getRole().getName() + ") ")
                .clientId(bookCarRequest.getClientId())
                .carId(bookCarRequest.getCarId())
                .createdAt(createdAt)
                .lockedFrom(lockedFrom)
                .pickupDateTime(bookCarRequest.getPickupDateTime())
                .dropOffDateTime(bookCarRequest.getDropOffDateTime())
                .pickupLocationId(bookCarRequest.getPickupLocationId())
                .dropOffLocationId(bookCarRequest.getDropOffLocationId())
                .supportAgentId(locationDao.findById(bookCarRequest.getPickupLocationId()).getSupportAgentId())
                .carMileageStart(0)
                .carMileageEnd(0)
                .build();
    }

    private Booking toEditBooking(Booking booking, BookCarEditRequest bookCarRequest) {
        String lockedFrom = StringDateConverter.getLockedDateTime(bookCarRequest.getPickupDateTime());

        return booking.toBuilder()
                .lockedFrom(lockedFrom)
                .pickupDateTime(bookCarRequest.getPickupDateTime())
                .dropOffDateTime(bookCarRequest.getDropOffDateTime())
                .pickupLocationId(bookCarRequest.getPickupLocationId())
                .dropOffLocationId(bookCarRequest.getDropOffLocationId())
                .build();
    }
}
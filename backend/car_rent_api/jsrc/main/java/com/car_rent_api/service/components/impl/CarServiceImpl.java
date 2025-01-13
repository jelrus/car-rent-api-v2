package com.car_rent_api.service.components.impl;

import com.car_rent_api.config.TableKeys;
import com.car_rent_api.exception.ExistenceException;
import com.car_rent_api.persistence.dao.components.CarDao;
import com.car_rent_api.persistence.dao.components.LocationDao;
import com.car_rent_api.persistence.models.dto.cars.*;
import com.car_rent_api.persistence.models.entity.Car;
import com.car_rent_api.persistence.models.entity.Location;
import com.car_rent_api.persistence.models.entity.types.CarCategory;
import com.car_rent_api.persistence.models.entity.types.CarFuelType;
import com.car_rent_api.persistence.models.entity.types.CarGearBoxType;
import com.car_rent_api.persistence.pagination.api.TableRequest;
import com.car_rent_api.persistence.pagination.api.TableResponse;
import com.car_rent_api.persistence.pagination.api.PaginationRequest;
import com.car_rent_api.persistence.pagination.api.SpecificationRequest;
import com.car_rent_api.persistence.pagination.type.JoinType;
import com.car_rent_api.persistence.pagination.type.ValueType;
import com.car_rent_api.service.components.CarService;
import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.StringDateConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CarServiceImpl implements CarService {

    private final CarDao carDao;
    private final LocationDao locationDao;

    public CarServiceImpl(CarDao carDao, LocationDao locationDao) {
        this.carDao = carDao;
        this.locationDao = locationDao;
    }

    @Override
    public CarDetailsResponse findById(String id) {
        checkCarExistence(id);
        return toCarDetailsResponse(carDao.findById(id));
    }

    @Override
    public CarBookedDatesResponse getBookedDays(String id) {
        checkCarExistence(id);
        return CarBookedDatesResponse.builder().content(carDao.findById(id).getBookedDays()).build();
    }

    @Override
    public PopularCarsResponse findByCategorySortedByRating(Map<String, String> params) {
        TableRequest tableRequest = TableRequest.builder()
                .pagination(PaginationRequest.builder()
                        .defaultSort(TableKeys.CAR_RENTAL_EXPERIENCE_IDX)
                        .defaultDirection(false)
                        .build())
                .specification(SpecificationRequest.builder()
                        .equalTo(ValueType.STRING_UPPERCASE, "CAR#CATEGORY", params.get("category"))
                        .notEqualTo(ValueType.STRING_UPPERCASE, "CAR#STATUS", "UNAVAILABLE")
                        .build(JoinType.AND))
                .build();

        return toPopularCarsResponse(carDao.findByTableRequestIndexed(tableRequest));
    }

    @Override
    public FilterCarsPageableResponse findByHomeSearchFilter(Map<String, String> params) {
        List<String> datesRange = StringDateConverter.generateGermanDatesRange(params.get("pickupDateTime"),
                params.get("dropOffDateTime"));

        TableRequest tableRequest = TableRequest.builder()
                .pagination(PaginationRequest.builder()
                        .page(params.get("page"), 1)
                        .size(params.get("size"), 16)
                        .defaultSort(TableKeys.CAR_STATUS_IDX)
                        .defaultDirection(true)
                        .build())
                .specification(SpecificationRequest.builder()
                        .equalTo(ValueType.STRING, "CAR#PICKUP_LOCATION_ID", params.get("pickupLocationId"))
                        .contains(ValueType.STRING, "CAR#DROPOFF_LOCATIONS_IDS", params.get("dropOffLocationId"))
                        .allNotInListRange(ValueType.STRING, "CAR#BOOKED_DAYS", datesRange)
                        .equalTo(ValueType.STRING_UPPERCASE, "CAR#CATEGORY", params.get("category"))
                        .equalTo(ValueType.STRING_UPPERCASE, "CAR#GEAR_BOX_TYPE", params.get("gearBoxType"))
                        .equalTo(ValueType.STRING_UPPERCASE, "CAR#FUEL_TYPE", params.get("fuelType"))
                        .between(ValueType.NUMBER, "CAR#PRICE_PER_DAY", params.get("minPrice"), params.get("maxPrice"))
                        .build(JoinType.AND))
                .build();

        TableResponse<Car> carFilterTableResponse = carDao.findByTableRequestIndexedPaginated(tableRequest);
        Map<String, Object> carFilterComponents = generateFilterComponents();

        return toFilterCarsPageableResponse(carFilterTableResponse, carFilterComponents);
    }

    private void checkCarExistence(String id) {
        if (!carDao.isExistsById(id)) {
            LogPrinter.error("[CarService] Car does not exist in database");
            throw new ExistenceException("Car does not exist");
        }
    }

    private Map<String, Object> generateFilterComponents() {
        Map<String, Object> components = new LinkedHashMap<>();
        String serverTime = StringDateConverter.toISO8601DateTimeRounded(LocalDateTime.now(ZoneId.of("Europe/Kiev")));
        components.put("serverTime", serverTime);
        components.put("minPrice", carDao.minPrice());
        components.put("maxPrice", carDao.maxPrice());
        components.put("locations", locationDao.findAll().stream().map(Location::getName).toList());
        components.put("category", Arrays.stream(CarCategory.values()).map(c -> c.getName().toUpperCase()).toList());
        components.put("gearBoxType", Arrays.stream(CarGearBoxType.values()).map(gb -> gb.getName().toUpperCase())
                .toList());
        components.put("fuelType", Arrays.stream(CarFuelType.values()).map(ft -> ft.getName().toUpperCase()).toList());
        return components;
    }

    private FilterCarsPageableResponse toFilterCarsPageableResponse(TableResponse<Car> tableResponse,
                                                                    Map<String, Object> components) {
        return FilterCarsPageableResponse.builder()
                .content(tableResponse.getItems().stream().map(toCarBriefInfo()).toList())
                .elementsOnPage(tableResponse.getElementsOnPage())
                .totalElements(tableResponse.getTotalElements())
                .currentPage(tableResponse.getPage())
                .totalPages(tableResponse.getTotalPages())
                .components(components)
                .build();
    }

    private PopularCarsResponse toPopularCarsResponse(TableResponse<Car> carTableResponse) {
        return PopularCarsResponse.builder()
                .content(carTableResponse.getItems().stream().map(toCarBriefInfo()).toList())
                .build();
    }

    private Function<Car, CarBriefInfo> toCarBriefInfo() {
        return c -> CarBriefInfo.builder()
                .carId(c.getSkId())
                .imageUrl(c.getImageUrl())
                .location(locationDao.findById(c.getPickupLocationId()).getName())
                .pickupLocationId(c.getPickupLocationId())
                .dropOffLocationsIds(c.getDropOffLocationsIds())
                .model(c.getModel())
                .pricePerDay(String.valueOf(c.getPricePerDay()))
                .rentalExperience(c.getRentalExperience())
                .status(c.getStatus().getName())
                .build();
    }

    private CarDetailsResponse toCarDetailsResponse(Car car) {
        return CarDetailsResponse.builder()
                .carId(car.getSkId())
                .rentalExperience(car.getRentalExperience())
                .climateControlOption(car.getClimateControlOption().getName())
                .engineCapacity(car.getEngineCapacity())
                .fuelConsumption(car.getFuelConsumption())
                .fuelType(car.getFuelType().getName())
                .gearBoxType(car.getGearBoxType().getName())
                .images(car.getImages())
                .location(locationDao.findById(car.getPickupLocationId()).getName())
                .pickupLocationId(car.getPickupLocationId())
                .dropOffLocationsIds(car.getDropOffLocationsIds())
                .model(car.getModel())
                .passengerCapacity(car.getPassengerCapacity())
                .pricePerDay(String.valueOf(car.getPricePerDay()))
                .status(car.getStatus().getName())
                .build();
    }
}
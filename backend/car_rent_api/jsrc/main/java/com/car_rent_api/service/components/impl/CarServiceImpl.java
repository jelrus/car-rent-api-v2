package com.car_rent_api.service.components.impl;

import com.car_rent_api.config.TableKeys;
import com.car_rent_api.exception.ExistenceException;
import com.car_rent_api.persistence.dao.components.CarDao;
import com.car_rent_api.persistence.dao.components.LocationDao;
import com.car_rent_api.persistence.models.dto.cars.*;
import com.car_rent_api.persistence.models.entity.Car;
import com.car_rent_api.persistence.pagination.api.TableRequest;
import com.car_rent_api.persistence.pagination.api.TableResponse;
import com.car_rent_api.persistence.pagination.api.PaginationRequest;
import com.car_rent_api.persistence.pagination.api.SpecificationRequest;
import com.car_rent_api.persistence.pagination.type.JoinType;
import com.car_rent_api.persistence.pagination.type.ValueType;
import com.car_rent_api.service.components.CarService;
import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.StringDateConverter;

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
                        .build(JoinType.AND))
                .build();

        return toPopularCarsResponse(carDao.findByTableRequestIndexed(tableRequest));
    }

    @Override
    public FilterCarsPageableResponse findCarsByHomeSearchFilter(Map<String, String> params) {
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
                        .notInRange(ValueType.STRING, "CAR#BOOKED_DAYS", datesRange)
                        .equalTo(ValueType.STRING_UPPERCASE, "CAR#CATEGORY", params.get("category"))
                        .equalTo(ValueType.STRING_UPPERCASE, "CAR#GEAR_BOX_TYPE", params.get("gearBoxType"))
                        .equalTo(ValueType.STRING_UPPERCASE, "CAR#FUEL_TYPE", params.get("fuelType"))
                        .between(ValueType.NUMBER, "CAR#PRICE_PER_DAY", params.get("minPrice"), params.get("maxPrice"))
                        .build(JoinType.AND))
                .build();

        return toFilterCarsPageableResponse(carDao.findByTableRequestIndexedPaginated(tableRequest));
    }

    private void checkCarExistence(String id) {
        if (!carDao.isExistsById(id)) {
            LogPrinter.error("[CarService] Car does not exist in database");
            throw new ExistenceException("Car does not exist");
        }
    }

    private FilterCarsPageableResponse toFilterCarsPageableResponse(TableResponse<Car> tableResponse) {
        return FilterCarsPageableResponse.builder()
                .content(tableResponse.getItems().stream().map(toCarBriefInfo()).toList())
                .elementsOnPage(tableResponse.getElementsOnPage())
                .totalElements(tableResponse.getTotalElements())
                .currentPage(tableResponse.getPage())
                .totalPages(tableResponse.getTotalPages())
                .build();
    }

    private PopularCarsResponse toPopularCarsResponse(TableResponse<Car> carTableResponse) {
        return PopularCarsResponse.builder()
                .content(carTableResponse.getItems().stream().map(toCarBriefInfo()).toList())
                .build();
    }

    private Function<Car, CarBriefInfo> toCarBriefInfo() {
        return s -> CarBriefInfo.builder()
                .carId(s.getSkId())
                .imageUrl(s.getImageUrl())
                .location(locationDao.findById(s.getPickupLocationId()).getName())
                .model(s.getModel())
                .pricePerDay(String.valueOf(s.getPricePerDay()))
                .rentalExperience(s.getRentalExperience())
                .status(s.getStatus().getName())
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
                .model(car.getModel())
                .passengerCapacity(car.getPassengerCapacity())
                .pricePerDay(String.valueOf(car.getPricePerDay()))
                .status(car.getStatus().getName())
                .build();
    }
}
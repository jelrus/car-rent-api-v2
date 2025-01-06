package com.car_rent_api.service.components.impl;

import com.car_rent_api.exception.ExistenceException;
import com.car_rent_api.persistence.dao.components.CarDao;
import com.car_rent_api.persistence.dao.components.LocationDao;
import com.car_rent_api.persistence.models.dto.cars.*;
import com.car_rent_api.persistence.models.entity.Car;
import com.car_rent_api.persistence.specification.CarPageRequest;
import com.car_rent_api.persistence.specification.CarPageResponse;
import com.car_rent_api.service.components.CarService;
import com.car_rent_api.utils.components.LogPrinter;

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
        LogPrinter.info("[CarService] Looking for the car with id {}", id);

        if (carDao.isExistsById(id)) {
            return toCarDetailsResponse(carDao.findById(id));
        } else {
            LogPrinter.error("[CarService] Car does not exist in database");
            throw new ExistenceException("Car does not exist");
        }
    }

    @Override
    public CarBookedDatesResponse getBookedDays(String id) {
        LogPrinter.info("[CarService] Looking for the car with id {}", id);

        if (carDao.isExistsById(id)) {
            return CarBookedDatesResponse.builder().content(carDao.findById(id).getBookedDays()).build();
        } else {
            LogPrinter.error("[CarService] Car does not exist in database");
            throw new ExistenceException("Car does not exist");
        }
    }

    @Override
    public PopularCarsResponse findCarsByCategorySortedByRentalExperience(Map<String, String> params) {
        LogPrinter.info("[CarService] Entering findCarsByCategorySortedByRentalExperience");
        CarPageRequest carPageRequest = CarPageRequest.builder()
                .init(params)
                .categoryEquals()
                .build();

        LogPrinter.info("[CarService] Page request {}", carPageRequest.getFilter());
        return toPopularCarsResponse(carDao.findCarsByCategorySortedByRentalExperience(carPageRequest));
    }

    @Override
    public FilterCarsPageableResponse findCarsFiltered(Map<String, String> params) {
        LogPrinter.info("[CarService] Entering findCarsFiltered");
        CarPageRequest carPageRequest = CarPageRequest.builder()
                .init(params)
                .pickupLocationIdEquals()
                .dropOffLocationIdInRangeDropOffLocationsIds()
                .pickupAndDropOffDatesRangeNotOverlappingBookedDays()
                .categoryEquals()
                .gearBoxTypeEquals()
                .fuelTypeEquals()
                .priceInBetweenMinAndMaxPrices()
                .toPage()
                .forSize()
                .build();

        LogPrinter.info("[CarService] Page request {}", carPageRequest.getFilter());
        return toFilterCarsPageableResponse(carDao.findCarsFiltered(carPageRequest));
    }

    private FilterCarsPageableResponse toFilterCarsPageableResponse(CarPageResponse carPageResponse) {
        LogPrinter.info("[CarService] Converting PageResponse");
        return FilterCarsPageableResponse.builder()
                .content(carPageResponse.getCars().stream().map(toCarBriefInfo()).toList())
                .elementsOnPage(carPageResponse.getElementsOnPage())
                .totalElements(carPageResponse.getTotalElements())
                .currentPage(carPageResponse.getCurrentPage())
                .totalPages(carPageResponse.getTotalPages())
                .build();
    }

    private PopularCarsResponse toPopularCarsResponse(CarPageResponse carPageResponse) {
        LogPrinter.info("[CarService] Converting PageResponse");
        return PopularCarsResponse.builder()
                .content(carPageResponse.getCars().stream().map(toCarBriefInfo()).toList())
                .build();
    }

    private Function<Car, CarBriefInfo> toCarBriefInfo() {
        LogPrinter.info("[CarService] Converting Car to CarBriefInfo");
        return s ->  CarBriefInfo.builder()
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
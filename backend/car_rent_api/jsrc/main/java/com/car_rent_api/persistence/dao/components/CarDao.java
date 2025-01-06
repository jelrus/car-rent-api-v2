package com.car_rent_api.persistence.dao.components;

import com.car_rent_api.persistence.models.entity.Car;
import com.car_rent_api.persistence.specification.CarPageRequest;
import com.car_rent_api.persistence.specification.CarPageResponse;

public interface CarDao {

    Car put(Car car);

    Car findById(String id);

    Boolean isExistsById(String id);

    Boolean isBookedDatesAreFree(CarPageRequest pageRequest);

    CarPageResponse findCarsByCategorySortedByRentalExperience(CarPageRequest carPageRequest);

    CarPageResponse findCarsFiltered(CarPageRequest carPageRequest);
}
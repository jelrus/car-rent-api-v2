package com.api.persistence.dao.components;

import com.api.persistence.models.entity.Car;
import com.api.persistence.specification.CarPageRequest;
import com.api.persistence.specification.CarPageResponse;

public interface CarDao {

    Car put(Car car);

    Car findById(String id);

    Boolean isExistsById(String id);

    Boolean isBookedDatesAreFree(CarPageRequest pageRequest);

    CarPageResponse findCarsByCategorySortedByRentalExperience(CarPageRequest carPageRequest);

    CarPageResponse findCarsFiltered(CarPageRequest carPageRequest);
}
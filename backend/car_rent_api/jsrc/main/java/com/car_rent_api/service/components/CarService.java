package com.car_rent_api.service.components;

import com.car_rent_api.persistence.models.dto.cars.CarBookedDatesResponse;
import com.car_rent_api.persistence.models.dto.cars.CarDetailsResponse;
import com.car_rent_api.persistence.models.dto.cars.FilterCarsPageableResponse;
import com.car_rent_api.persistence.models.dto.cars.PopularCarsResponse;

import java.util.Map;

public interface CarService {

    CarDetailsResponse findById(String id);

    CarBookedDatesResponse getBookedDays(String id);

    PopularCarsResponse findCarsByCategorySortedByRentalExperience(Map<String, String> params);

    FilterCarsPageableResponse findCarsFiltered(Map<String, String> params);
}
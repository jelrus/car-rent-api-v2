package com.api.service.components;

import com.api.persistence.models.dto.cars.CarBookedDatesResponse;
import com.api.persistence.models.dto.cars.CarDetailsResponse;
import com.api.persistence.models.dto.cars.FilterCarsPageableResponse;
import com.api.persistence.models.dto.cars.PopularCarsResponse;

import java.util.Map;

public interface CarService {

    CarDetailsResponse findById(String id);

    CarBookedDatesResponse getBookedDays(String id);

    PopularCarsResponse findCarsByCategorySortedByRentalExperience(Map<String, String> params);

    FilterCarsPageableResponse findCarsFiltered(Map<String, String> params);
}
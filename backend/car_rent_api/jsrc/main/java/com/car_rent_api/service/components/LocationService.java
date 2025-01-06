package com.car_rent_api.service.components;

import com.car_rent_api.persistence.models.dto.location.LocationsResponse;

public interface LocationService {

    LocationsResponse findAll();
}
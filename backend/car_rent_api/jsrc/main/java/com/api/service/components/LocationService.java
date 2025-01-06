package com.api.service.components;

import com.api.persistence.models.dto.location.LocationsResponse;

public interface LocationService {

    LocationsResponse findAll();
}
package com.backend.mapper;

import com.backend.models.dto.response.general.LocationResponse;
import com.backend.models.table.Location;

public class LocationMapper {

    public static LocationResponse.LocationInfo toLocationResponse(Location location) {
        LocationResponse.LocationInfo locationInfoResponse = new LocationResponse.LocationInfo();
        locationInfoResponse.setLocationId(location.getLocationId());
        locationInfoResponse.setLocationName(location.getLocationName());
        locationInfoResponse.setLocationAddress(location.getLocationAddress());
        locationInfoResponse.setLocationImageUrl(location.getLocationImageUrl());
        return locationInfoResponse;
    }
}
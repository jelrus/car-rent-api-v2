package com.car_rent_api.service.components.impl;

import com.car_rent_api.persistence.dao.components.LocationDao;
import com.car_rent_api.persistence.models.dto.location.LocationInfo;
import com.car_rent_api.persistence.models.dto.location.LocationsResponse;
import com.car_rent_api.persistence.models.entity.Location;
import com.car_rent_api.service.components.LocationService;

import java.util.List;
import java.util.function.Function;

public class LocationServiceImpl implements LocationService {

    private final LocationDao locationDao;

    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public LocationsResponse findAll() {
        return toLocationsResponse(locationDao.findAll());
    }

    private LocationsResponse toLocationsResponse(List<Location> locations) {
        return LocationsResponse.builder().content(locations.stream().map(toLocationInfo()).toList()).build();
    }

    private Function<Location, LocationInfo> toLocationInfo() {
        return s ->  LocationInfo.builder()
                .locationId(s.getSkId())
                .locationName(s.getName())
                .locationAddress(s.getAddress())
                .locationImageUrl(s.getImageUrl())
                .build();
    }
}
package com.car_rent_api.service.components.impl;

import com.car_rent_api.persistence.dao.components.LocationDao;
import com.car_rent_api.persistence.models.dto.location.LocationInfo;
import com.car_rent_api.persistence.models.dto.location.LocationsResponse;
import com.car_rent_api.persistence.models.entity.Location;
import com.car_rent_api.service.components.LocationService;
import com.car_rent_api.utils.components.LogPrinter;

import java.util.List;
import java.util.function.Function;

public class LocationServiceImpl implements LocationService {

    private final LocationDao locationDao;

    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public LocationsResponse findAll() {
        LogPrinter.warn("[LocationService | Find All] Entering 'findAll @ LocationService' method");
        LocationsResponse locationsResponse = toLocationsResponse(locationDao.findAll());
        Integer quantity = locationsResponse.getContent().size();
        LogPrinter.warn("[LocationService | Find All] {} Faq Story(ies) were found", quantity);
        return locationsResponse;
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
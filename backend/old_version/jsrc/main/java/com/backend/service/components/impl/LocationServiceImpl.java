package com.backend.service.components.impl;

import com.backend.dao.components.LocationDao;
import com.backend.exception.ContentNotFoundException;
import com.backend.mapper.LocationMapper;
import com.backend.models.dto.response.general.LocationResponse;
import com.backend.models.table.Location;
import com.backend.service.components.LocationService;
import com.google.gson.Gson;

import java.util.List;

public class LocationServiceImpl implements LocationService {

    private final LocationDao locationDao;

    private final Gson gson;

    public LocationServiceImpl(LocationDao locationDao, Gson gson) {
        this.locationDao = locationDao;
        this.gson = gson;
    }

    @Override
    public LocationResponse findAll() {
        List<Location> locations = locationDao.findAll();

        if (locations == null || locations.isEmpty()) {
            throw new ContentNotFoundException("Locations could not be found");
        }

        LocationResponse locationsResponse = new LocationResponse();
        locationsResponse.setContent(locations.stream().map(LocationMapper::toLocationResponse).toList());

        return locationsResponse;
    }
}
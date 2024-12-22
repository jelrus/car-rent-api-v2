package com.backend.service.impl;

import com.backend.dao.LocationDao;
import com.backend.mapper.LocationMapper;
import com.backend.models.dto.request.LocationInfo;
import com.backend.models.dto.response.LocationResponse;
import com.backend.service.LocationService;

import java.util.ArrayList;
import java.util.List;

public class LocationServiceImpl implements LocationService {

    private final LocationDao locationDao;

    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }


    @Override
    public LocationResponse findAll() {
        List<LocationInfo> rsl = new ArrayList<>();

        locationDao.findAll()
                .forEach(locationEntity ->
                        rsl.add(LocationMapper.locationEntityToLocationInfo(locationEntity)));

        return new LocationResponse(rsl);
    }
}

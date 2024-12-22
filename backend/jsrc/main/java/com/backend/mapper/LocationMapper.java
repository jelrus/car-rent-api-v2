package com.backend.mapper;

import com.backend.models.dto.request.CarBriefInfo;
import com.backend.models.dto.request.LocationInfo;
import com.backend.models.table.CarEntity;
import com.backend.models.table.LocationEntity;

public class LocationMapper {

    public static LocationInfo locationEntityToLocationInfo(LocationEntity locationEntity) {

        LocationInfo locationInfo = new LocationInfo();
       locationInfo.setLocationId(locationEntity.getLocationId());
       locationInfo.setLocationAddress(locationEntity.getLocationAddress());
       locationInfo.setLocationName(locationEntity.getLocationName());
       locationInfo.setLocationImageUrl(locationEntity.getLocationImageUrl());
        return locationInfo;
    }
}

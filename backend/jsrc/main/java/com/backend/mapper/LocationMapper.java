package com.backend.mapper;

import com.backend.models.dto.request.LocationInfo;
import com.backend.models.table.LocationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")

public interface LocationMapper {

    LocationInfo locationEntityToLocationInfo(LocationEntity locationEntity);
}


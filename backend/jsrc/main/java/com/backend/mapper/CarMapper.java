package com.backend.mapper;

import com.backend.models.dto.request.CarBriefInfo;
import com.backend.models.table.CarEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface CarMapper {
    CarBriefInfo carEntityToCarBriefInfo(CarEntity car);
}

package com.backend.mapper;

import com.backend.models.dto.request.CarBriefInfo;
import com.backend.models.table.CarEntity;

public class CarMapper {

    public static CarBriefInfo carEntityToCarBriefInfo(CarEntity carEntity) {

        CarBriefInfo carBriefInfo = new CarBriefInfo();
        carBriefInfo.setStatus(carEntity.getStatus());
        carBriefInfo.setPricePerDay(carEntity.getPricePerDay());
        carBriefInfo.setServiceRating(carEntity.getServiceRating());
        carBriefInfo.setModel(carEntity.getModel());
        carBriefInfo.setCarId(carEntity.getCarId());
        carBriefInfo.setCarRating(carEntity.getCarRating());
        carBriefInfo.setImageUrl(carEntity.getImageUrl());
        carBriefInfo.setLocation(carEntity.getLocation());

        return carBriefInfo;
    }
}

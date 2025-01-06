package com.car_rent_api.persistence.dao.components;

import com.car_rent_api.persistence.models.entity.Location;

import java.util.List;

public interface LocationDao {

    Location findById(String id);

    Boolean isExistById(String id);

    List<Location> findAll();
}
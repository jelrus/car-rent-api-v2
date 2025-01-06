package com.api.persistence.dao.components;

import com.api.persistence.models.entity.Location;

import java.util.List;

public interface LocationDao {

    Location findById(String id);

    Boolean isExistById(String id);

    List<Location> findAll();
}
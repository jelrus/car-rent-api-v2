package com.backend.dao;

import com.backend.models.table.LocationEntity;

import java.util.List;

public interface LocationDao {
    LocationEntity create(LocationEntity locationEntity);

    List<LocationEntity> findAll();
}

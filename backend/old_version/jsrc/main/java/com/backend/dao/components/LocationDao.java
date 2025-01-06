package com.backend.dao.components;

import com.backend.models.table.Location;

import java.util.List;

public interface LocationDao {

    List<Location> findAll();
}
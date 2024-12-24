package com.backend.dao;

import com.backend.models.table.CarEntity;

public interface CarDao {

    CarEntity findByCarId(String carId);

    Boolean existsByCarId(String carId);

}
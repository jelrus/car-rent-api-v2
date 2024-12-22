package com.backend.service;

import com.backend.models.table.CarEntity;

public interface CarService {

    CarEntity findByCarId(String carId);

    void existsByCarId(String carId);
}

package com.backend.dao;

import com.backend.models.table.CarEntity;
import com.backend.models.table.types.CarCategory;

import java.util.List;

public interface PopularCarsDao {
    CarEntity create(CarEntity car);

    List<CarEntity> findAllByCategory(CarCategory carCategory);
}

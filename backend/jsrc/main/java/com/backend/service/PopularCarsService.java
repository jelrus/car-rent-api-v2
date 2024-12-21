package com.backend.service;

import com.backend.models.dto.response.PopularCarsResponse;
import com.backend.models.table.types.CarCategory;


public interface PopularCarsService {

    PopularCarsResponse findAllByCategory(CarCategory carCategory);
}

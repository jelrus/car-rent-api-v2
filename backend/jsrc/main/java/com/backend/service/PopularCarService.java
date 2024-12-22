package com.backend.service;

import com.backend.models.dto.response.PopularCarResponse;
import com.backend.models.table.types.CarCategory;


public interface PopularCarService {

    PopularCarResponse findAllByCategory(CarCategory carCategory);
}

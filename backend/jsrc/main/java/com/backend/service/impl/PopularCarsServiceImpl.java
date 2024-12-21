package com.backend.service.impl;

import com.backend.dao.PopularCarsDao;
import com.backend.mapper.CarMapper;
import com.backend.models.dto.request.CarBriefInfo;
import com.backend.models.dto.response.PopularCarsResponse;
import com.backend.models.table.types.CarCategory;
import com.backend.service.PopularCarsService;

import java.util.ArrayList;
import java.util.List;

public class PopularCarsServiceImpl implements PopularCarsService {

    private final PopularCarsDao popularCarsDao;

    public PopularCarsServiceImpl(PopularCarsDao popularCarsDao) {
        this.popularCarsDao = popularCarsDao;
    }

    @Override
    public PopularCarsResponse findAllByCategory(CarCategory carCategory) {
        List<CarBriefInfo> rsl = new ArrayList<>();

        popularCarsDao.findAllByCategory(carCategory)
                .forEach(carEntity ->
                        rsl.add(CarMapper.carEntityToCarBriefInfo(carEntity)));
        return new PopularCarsResponse(rsl);
    }
}

package com.backend.service.impl;

import com.backend.dao.PopularCarDao;
import com.backend.mapper.CarMapper;
import com.backend.models.dto.request.CarBriefInfo;
import com.backend.models.dto.response.PopularCarResponse;
import com.backend.models.table.types.CarCategory;
import com.backend.service.PopularCarService;

import java.util.ArrayList;
import java.util.List;

public class PopularCarServiceImpl implements PopularCarService {

    private final PopularCarDao popularCarDao;

    public PopularCarServiceImpl(PopularCarDao popularCarsDao) {
        this.popularCarDao = popularCarsDao;
    }

    @Override
    public PopularCarResponse findAllByCategory(CarCategory carCategory) {
        List<CarBriefInfo> rsl = new ArrayList<>();

        popularCarDao.findAllByCategory(carCategory)
                .forEach(carEntity ->
                        rsl.add(CarMapper.carEntityToCarBriefInfo(carEntity)));
        return new PopularCarResponse(rsl);
    }
}

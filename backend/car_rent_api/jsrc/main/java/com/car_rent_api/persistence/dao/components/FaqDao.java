package com.car_rent_api.persistence.dao.components;

import com.car_rent_api.persistence.models.entity.FaqStory;

import java.util.List;

public interface FaqDao {

    List<FaqStory> findAll();
}
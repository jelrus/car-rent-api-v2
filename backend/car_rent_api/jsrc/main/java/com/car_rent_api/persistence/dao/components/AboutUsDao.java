package com.car_rent_api.persistence.dao.components;

import com.car_rent_api.persistence.models.entity.AboutUsStory;

import java.util.List;

public interface AboutUsDao {

    List<AboutUsStory> findAll();
}
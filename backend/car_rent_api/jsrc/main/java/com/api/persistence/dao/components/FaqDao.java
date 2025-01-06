package com.api.persistence.dao.components;

import com.api.persistence.models.entity.FaqStory;

import java.util.List;

public interface FaqDao {

    List<FaqStory> findAll();
}
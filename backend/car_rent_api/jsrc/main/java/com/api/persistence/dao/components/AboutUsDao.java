package com.api.persistence.dao.components;

import com.api.persistence.models.entity.AboutUsStory;

import java.util.List;

public interface AboutUsDao {

    List<AboutUsStory> findAll();
}
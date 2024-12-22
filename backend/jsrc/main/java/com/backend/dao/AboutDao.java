package com.backend.dao;

import com.backend.models.table.AboutEntity;

import java.util.List;

public interface AboutDao {
    AboutEntity create(AboutEntity about);

    List<AboutEntity> findAll();
}

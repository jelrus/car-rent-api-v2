package com.backend.dao;

import com.backend.models.table.FaqEntity;

import java.util.List;

public interface FaqDao {
    FaqEntity create(FaqEntity faq);

    List<FaqEntity> findAll();
}

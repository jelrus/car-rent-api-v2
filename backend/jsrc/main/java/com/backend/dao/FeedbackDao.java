package com.backend.dao;

import com.backend.models.table.FeedbackEntity;

import java.util.List;

public interface FeedbackDao {
    FeedbackEntity create(FeedbackEntity feedback);

    List<FeedbackEntity> findAll();
}

package com.backend.service.components.impl;

import com.backend.dao.components.ReviewDao;
import com.backend.models.dto.response.ux.FeedbacksResponse;
import com.backend.service.components.ReviewService;
import com.google.gson.Gson;

public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;

    private final Gson gson;

    public ReviewServiceImpl(ReviewDao reviewDao, Gson gson) {
        this.reviewDao = reviewDao;
        this.gson = gson;
    }

    @Override
    public FeedbacksResponse findRecentFeedbacksSortByPopularCars() {
        return null;
    }
}

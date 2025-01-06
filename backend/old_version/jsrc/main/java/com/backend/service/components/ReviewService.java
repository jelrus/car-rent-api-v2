package com.backend.service.components;

import com.backend.models.dto.response.ux.FeedbacksResponse;

public interface ReviewService {

    FeedbacksResponse findRecentFeedbacksSortByPopularCars();
}
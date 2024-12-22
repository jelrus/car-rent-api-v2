package com.backend.service;

import com.backend.models.dto.response.FeedbacksResponse;


public interface FeedbackService {
    FeedbacksResponse findAll();
}

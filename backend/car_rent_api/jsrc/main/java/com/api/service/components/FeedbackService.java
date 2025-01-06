package com.api.service.components;

import com.api.persistence.models.dto.feedback.ClientReviewSortedPageableResponse;
import com.api.persistence.models.dto.feedback.FeedbacksResponse;

import java.util.Map;

public interface FeedbackService {

    FeedbacksResponse findFeedbacksSortedByRentalExperience();

    ClientReviewSortedPageableResponse findFeedbacksFilteredByCarIdAndSorted(Map<String, String> params, String carId);
}
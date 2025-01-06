package com.car_rent_api.service.components;

import com.car_rent_api.persistence.models.dto.feedback.ClientReviewSortedPageableResponse;
import com.car_rent_api.persistence.models.dto.feedback.FeedbacksResponse;

import java.util.Map;

public interface FeedbackService {

    FeedbacksResponse findFeedbacksSortedByRentalExperience();

    ClientReviewSortedPageableResponse findFeedbacksFilteredByCarIdAndSorted(Map<String, String> params, String carId);
}
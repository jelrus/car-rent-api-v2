package com.car_rent_api.persistence.dao.components;

import com.car_rent_api.persistence.models.entity.Feedback;
import com.car_rent_api.persistence.specification.FeedbackPageRequest;
import com.car_rent_api.persistence.specification.FeedbackPageResponse;

import java.util.List;

public interface FeedbackDao {

    List<Feedback> findFeedbacksSortedByRentalExperience();

    FeedbackPageResponse findFeedbacksPaginatedAndFiltered(FeedbackPageRequest feedbackPageRequest);

    Feedback put(Feedback feedback);
}
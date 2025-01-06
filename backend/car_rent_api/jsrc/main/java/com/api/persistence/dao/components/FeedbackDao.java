package com.api.persistence.dao.components;

import com.api.persistence.models.entity.Feedback;
import com.api.persistence.specification.FeedbackPageRequest;
import com.api.persistence.specification.FeedbackPageResponse;

import java.util.List;

public interface FeedbackDao {

    List<Feedback> findFeedbacksSortedByRentalExperience();

    FeedbackPageResponse findFeedbacksPaginatedAndFiltered(FeedbackPageRequest feedbackPageRequest);
}
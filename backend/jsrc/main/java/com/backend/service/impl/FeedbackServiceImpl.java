package com.backend.service.impl;

import com.backend.dao.FeedbackDao;
import com.backend.mapper.FeedbackMapper;
import com.backend.models.dto.request.FeedbackInfo;
import com.backend.models.dto.response.FeedbacksResponse;
import com.backend.service.FeedbackService;

import java.util.ArrayList;
import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackDao feedbackDao;
    private final FeedbackMapper feedbackMapper;

    public FeedbackServiceImpl(FeedbackDao feedbackDao, FeedbackMapper feedbackMapper) {
        this.feedbackDao = feedbackDao;
        this.feedbackMapper = feedbackMapper;
    }


    @Override
    public FeedbacksResponse findAll() {
        List<FeedbackInfo> rsl = new ArrayList<>();
        feedbackDao.findAll()
                .forEach(feedbackEntity ->
                        rsl.add(feedbackMapper.feedbackEntityToFeedbackInfo(feedbackEntity)));
        return new FeedbacksResponse(rsl);

    }
}

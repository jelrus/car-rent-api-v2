package com.backend.models.dto.response;

import com.backend.models.dto.request.FeedbackInfo;

import java.util.List;

public class FeedbacksResponse {
    private List<FeedbackInfo> content;

    public FeedbacksResponse(List<FeedbackInfo> content) {
        this.content = content;
    }

    public FeedbacksResponse() {
    }

    public List<FeedbackInfo> getContent() {
        return content;
    }

    public void setContent(List<FeedbackInfo> content) {
        this.content = content;
    }
}

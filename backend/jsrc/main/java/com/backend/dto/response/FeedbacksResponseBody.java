package com.backend.dto.response;

import com.backend.dto.FeedbackInfo;

import java.util.List;

public class FeedbacksResponseBody {
    private List<FeedbackInfo> content;

    public FeedbacksResponseBody(List<FeedbackInfo> content) {
        this.content = content;
    }

    public FeedbacksResponseBody() {
    }

    public List<FeedbackInfo> getContent() {
        return content;
    }

    public void setContent(List<FeedbackInfo> content) {
        this.content = content;
    }
}

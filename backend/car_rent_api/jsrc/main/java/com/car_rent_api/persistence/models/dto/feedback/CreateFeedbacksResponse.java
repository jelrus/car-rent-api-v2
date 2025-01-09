package com.car_rent_api.persistence.models.dto.feedback;

import com.google.gson.annotations.Expose;

public class CreateFeedbacksResponse {

    @Expose
    private String feedbackId;

    @Expose
    private String systemMessage;

    public CreateFeedbacksResponse() {}

    public String getFeedbackId() {
        return feedbackId;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public static Builder builder() {
        return new CreateFeedbacksResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public CreateFeedbacksResponse.Builder feedbackId(String feedbackId) {
            CreateFeedbacksResponse.this.feedbackId = feedbackId;
            return this;
        }

        public CreateFeedbacksResponse.Builder systemMessage(String systemMessage) {
            CreateFeedbacksResponse.this.systemMessage = systemMessage;
            return this;
        }

        public CreateFeedbacksResponse build() {
            return CreateFeedbacksResponse.this;
        }
    }
}
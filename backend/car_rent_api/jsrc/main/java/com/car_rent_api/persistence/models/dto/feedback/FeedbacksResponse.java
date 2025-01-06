package com.car_rent_api.persistence.models.dto.feedback;

import com.google.gson.annotations.Expose;

import java.util.List;

public class FeedbacksResponse {

    @Expose
    private List<FeedbackInfo> content;

    public FeedbacksResponse() {}

    public List<FeedbackInfo> getContent() {
        return content;
    }

    public static Builder builder() {
        return new FeedbacksResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder content(List<FeedbackInfo> content) {
            FeedbacksResponse.this.content = content;
            return this;
        }

        public FeedbacksResponse build() {
            return FeedbacksResponse.this;
        }
    }
}
package com.car_rent_api.persistence.models.dto.faq;

import com.google.gson.annotations.Expose;

import java.util.List;

public class FaqResponse {

    @Expose
    private List<FaqStoryInfo> content;

    public FaqResponse() {}

    public List<FaqStoryInfo> getContent() {
        return content;
    }

    public static Builder builder() {
        return new FaqResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder content(List<FaqStoryInfo> content) {
            FaqResponse.this.content = content;
            return this;
        }

        public FaqResponse build() {
            return FaqResponse.this;
        }
    }
}
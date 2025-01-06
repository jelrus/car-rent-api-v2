package com.api.persistence.models.dto.about_us;

import com.google.gson.annotations.Expose;

import java.util.List;

public class AboutUsResponse {

    @Expose
    private List<AboutUsStoryInfo> content;

    public AboutUsResponse() {}

    public List<AboutUsStoryInfo> getContent() {
        return content;
    }

    public static Builder builder() {
        return new AboutUsResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder content(List<AboutUsStoryInfo> content) {
            AboutUsResponse.this.content = content;
            return this;
        }

        public AboutUsResponse build() {
            return AboutUsResponse.this;
        }
    }
}
package com.car_rent_api.persistence.models.dto.about_us;

import com.google.gson.annotations.Expose;

public class AboutUsStoryInfo {

    @Expose
    private String title;

    @Expose
    private String description;

    @Expose
    private String numericValue;

    public AboutUsStoryInfo() {}

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getNumericValue() {
        return numericValue;
    }

    public static Builder builder() {
        return new AboutUsStoryInfo().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder title(String title) {
            AboutUsStoryInfo.this.title = title;
            return this;
        }

        public Builder numericValue(String numericValue) {
            AboutUsStoryInfo.this.numericValue = numericValue;
            return this;
        }

        public Builder description(String description) {
            AboutUsStoryInfo.this.description = description;
            return this;
        }

        public AboutUsStoryInfo build() {
            return AboutUsStoryInfo.this;
        }
    }
}
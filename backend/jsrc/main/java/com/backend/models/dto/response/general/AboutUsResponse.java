package com.backend.models.dto.response.general;

import java.util.List;

public class AboutUsResponse {

    private List<AboutUsStoryInfo> content;

    public AboutUsResponse() {}

    public List<AboutUsStoryInfo> getContent() {
        return content;
    }

    public void setContent(List<AboutUsStoryInfo> content) {
        this.content = content;
    }

    public static class AboutUsStoryInfo {

        private String title;
        private String numericValue;
        private String description;

        public AboutUsStoryInfo() {}

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNumericValue() {
            return numericValue;
        }

        public void setNumericValue(String numericValue) {
            this.numericValue = numericValue;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
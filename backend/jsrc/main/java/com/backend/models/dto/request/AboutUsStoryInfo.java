package com.backend.models.dto.request;

public class AboutUsStoryInfo {
    private String description;
    private String numericValue;
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumericValue() {
        return numericValue;
    }

    public void setNumericValue(String numericValue) {
        this.numericValue = numericValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AboutUsStoryInfo() {
    }

    public AboutUsStoryInfo(String desription, String numericValue, String title) {
        this.description = desription;
        this.numericValue = numericValue;
        this.title = title;
    }
}

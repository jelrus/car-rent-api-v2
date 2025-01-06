package com.api.persistence.models.dto.feedback;

import com.api.config.TableKeys;
import com.google.gson.annotations.Expose;

public class FeedbackInfo {

    @Expose
    private String feedbackId;

    @Expose
    private String author;

    @Expose
    private String rentalExperience;

    @Expose
    private String carModel;

    @Expose
    private String carImageUrl;

    @Expose
    private String date;

    @Expose
    private String orderHistory;

    @Expose
    private String feedbackText;

    public FeedbackInfo() {}

    public String getFeedbackId() {
        return feedbackId;
    }

    public String getAuthor() {
        return author;
    }

    public String getRentalExperience() {
        return rentalExperience;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public String getDate() {
        return date;
    }

    public String getOrderHistory() {
        return orderHistory;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public static Builder builder() {
        return new FeedbackInfo().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder feedbackId(String feedbackId) {
            FeedbackInfo.this.feedbackId = feedbackId.replace(TableKeys.FEEDBACK_SK_PREFIX, "");
            return this;
        }

        public Builder author(String author) {
            FeedbackInfo.this.author = author;
            return this;
        }

        public Builder rentalExperience(String rentalExperience) {
            FeedbackInfo.this.rentalExperience = rentalExperience;
            return this;
        }

        public Builder carModel(String carModel) {
            FeedbackInfo.this.carModel = carModel;
            return this;
        }

        public Builder carImageUrl(String carImageUrl) {
            FeedbackInfo.this.carImageUrl = carImageUrl;
            return this;
        }

        public Builder date(String date) {
            FeedbackInfo.this.date = date;
            return this;
        }

        public Builder orderHistory(String orderHistory) {
            FeedbackInfo.this.orderHistory = orderHistory;
            return this;
        }

        public Builder feedbackText(String feedbackText) {
            FeedbackInfo.this.feedbackText = feedbackText;
            return this;
        }

        public FeedbackInfo build() {
            return FeedbackInfo.this;
        }
    }
}
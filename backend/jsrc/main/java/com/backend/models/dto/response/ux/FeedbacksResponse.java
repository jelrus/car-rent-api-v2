package com.backend.models.dto.response.ux;

import java.util.List;

public class FeedbacksResponse {

    private List<FeedbackInfo> content;

    public FeedbacksResponse() {}

    public List<FeedbackInfo> getContent() {
        return content;
    }

    public void setContent(List<FeedbackInfo> content) {
        this.content = content;
    }

    public static class FeedbackInfo {
        private String author;
        private String carImageUrl;
        private String carModel;
        private String carRating;
        private String date;
        private String feedbackId;
        private String feedbackText;
        private String orderHistory;
        private String serviceRating;

        public FeedbackInfo() {}

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCarImageUrl() {
            return carImageUrl;
        }

        public void setCarImageUrl(String carImageUrl) {
            this.carImageUrl = carImageUrl;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public String getCarRating() {
            return carRating;
        }

        public void setCarRating(String carRating) {
            this.carRating = carRating;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getFeedbackId() {
            return feedbackId;
        }

        public void setFeedbackId(String feedbackId) {
            this.feedbackId = feedbackId;
        }

        public String getFeedbackText() {
            return feedbackText;
        }

        public void setFeedbackText(String feedbackText) {
            this.feedbackText = feedbackText;
        }

        public String getOrderHistory() {
            return orderHistory;
        }

        public void setOrderHistory(String orderHistory) {
            this.orderHistory = orderHistory;
        }

        public String getServiceRating() {
            return serviceRating;
        }

        public void setServiceRating(String serviceRating) {
            this.serviceRating = serviceRating;
        }
    }
}
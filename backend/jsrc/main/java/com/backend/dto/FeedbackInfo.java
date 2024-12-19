package com.backend.dto;

public class FeedbackInfo {
    private String author;
    private String carImageUrl;
    private String carModel;
    private String carRating;
    private String date;
    private String feedbackId;
    private String feedbackText;
    private String orderHistory;
    private String serviceRating;

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

    public FeedbackInfo() {
    }

    public FeedbackInfo(String author, String carImageUrl, String carModel, String carRating, String date, String feedbackId, String feedbackText, String orderHistory, String serviceRating) {
        this.author = author;
        this.carImageUrl = carImageUrl;
        this.carModel = carModel;
        this.carRating = carRating;
        this.date = date;
        this.feedbackId = feedbackId;
        this.feedbackText = feedbackText;
        this.orderHistory = orderHistory;
        this.serviceRating = serviceRating;
    }
}

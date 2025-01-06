package com.backend.models.table;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

// Should be done after Bookings and Cars sections are completed
@DynamoDbBean
public class Review {

    private String feedbackId;
    private String date;
    private String carImageUrl;
    private String carModel;
    private String orderHistory;
    private String carRating;
    private String serviceRating;
    private String feedbackText;
    private String author;

    public Review() {}

    @DynamoDbPartitionKey
    @DynamoDbAttribute("feedbackId")
    public String getFeedbackId() {
        return feedbackId;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    @DynamoDbAttribute("carImageUrl")
    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }

    @DynamoDbAttribute("carModel")
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @DynamoDbAttribute("orderHistory")
    public String getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(String orderHistory) {
        this.orderHistory = orderHistory;
    }

    @DynamoDbAttribute("carRating")
    public String getCarRating() {
        return carRating;
    }

    public void setCarRating(String carRating) {
        this.carRating = carRating;
    }

    @DynamoDbAttribute("serviceRating")
    public String getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(String serviceRating) {
        this.serviceRating = serviceRating;
    }

    @DynamoDbAttribute("feedbackText")
    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    @DynamoDbAttribute("author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
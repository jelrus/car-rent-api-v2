package com.backend.models.table;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.Objects;

@DynamoDbBean
public class FeedbackEntity {

    private String author;
    private String carImageUrl;
    private String carModel;
    private String carRating;
    private String date;
    private String feedbackId;
    private String feedbackText;
    private String orderHistory;
    private String serviceRating;



    @DynamoDbAttribute("author")
    public String getAuthor() {
        return author;
    }

    @DynamoDbAttribute("carImageUrl")
    public String getCarImageUrl() {
        return carImageUrl;
    }
    @DynamoDbAttribute("carRating")
    public String getCarRating() {
        return carRating;
    }
    @DynamoDbAttribute("carModel")
    public String getCarModel() {
        return carModel;
    }
    @DynamoDbAttribute("date")
    public String getDate() {
        return date;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("feedbackId")
    public String getFeedbackId() {
        return feedbackId;
    }
    @DynamoDbAttribute("feedbackText")
    public String getFeedbackText() {
        return feedbackText;
    }
    @DynamoDbAttribute("orderHistory")
    public String getOrderHistory() {
        return orderHistory;
    }

    @DynamoDbAttribute("serviceRating")
    public String serviceRating() {
        return serviceRating;
    }

    @Override
    public String toString() {
        return "FeedbackEntity{" +
                "author='" + author + '\'' +
                ", carImageUrl='" + carImageUrl + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carRating='" + carRating + '\'' +
                ", date='" + date + '\'' +
                ", feedbackId='" + feedbackId + '\'' +
                ", feedbackText='" + feedbackText + '\'' +
                ", orderHistory='" + orderHistory + '\'' +
                ", serviceRating='" + serviceRating + '\'' +
                '}';
    }

    public FeedbackEntity(String author, String carImageUrl, String carModel, String carRating, String date, String feedbackId, String feedbackText, String orderHistory, String serviceRating) {
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

    public FeedbackEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackEntity that = (FeedbackEntity) o;
        return Objects.equals(author, that.author) && Objects.equals(carImageUrl, that.carImageUrl) && Objects.equals(carModel, that.carModel) && Objects.equals(carRating, that.carRating) && Objects.equals(date, that.date) && Objects.equals(feedbackId, that.feedbackId) && Objects.equals(feedbackText, that.feedbackText) && Objects.equals(orderHistory, that.orderHistory) && Objects.equals(serviceRating, that.serviceRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, carImageUrl, carModel, carRating, date, feedbackId, feedbackText, orderHistory, serviceRating);
    }
}
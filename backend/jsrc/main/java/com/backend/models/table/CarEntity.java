package com.backend.models.table;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class CarEntity {

    private String carId;
    private String carRating;
    private String imageUrl;
    private String model;
    private String location;
    private String pricePerDay;
    private String serviceRating;
    private String status;
    private String category;


    @DynamoDbPartitionKey
    @DynamoDbAttribute("carId")
    public String getCarId() {
        return carId;
    }


    @DynamoDbAttribute("serviceRating")
    public String getServiceRating() {
        return serviceRating;
    }

    @DynamoDbAttribute("pricePerDay")
    public String getPricePerDay() {
        return pricePerDay;
    }

    @DynamoDbAttribute("status")
    public String getStatus() {
        return status;
    }

    @DynamoDbAttribute("carRating")
    public String getCarRating() {
        return carRating;
    }

    @DynamoDbAttribute("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    @DynamoDbAttribute("model")
    public String getModel() {
        return model;
    }

    @DynamoDbAttribute("location")
    public String getLocation() {
        return location;
    }
    @DynamoDbAttribute("category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public CarEntity() {
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public void setCarRating(String carRating) {
        this.carRating = carRating;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setServiceRating(String serviceRating) {
        this.serviceRating = serviceRating;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "carId='" + carId + '\'' +
                ", carRating='" + carRating + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", model='" + model + '\'' +
                ", location='" + location + '\'' +
                ", pricePerDay='" + pricePerDay + '\'' +
                ", serviceRating='" + serviceRating + '\'' +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
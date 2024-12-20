package com.backend.models.dto.request;

public class CarBriefInfo {
    private String carId;
    private String carRating;
    private String imageUrl;
    private String location;
    private String model;
    private String pricePerDay;
    private String serviceRating;
    private String status;

    public CarBriefInfo(String carId, String carRating, String imageUrl, String location, String model, String pricePerDay, String serviceRating, String status) {
        this.carId = carId;
        this.carRating = carRating;
        this.imageUrl = imageUrl;
        this.location = location;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.serviceRating = serviceRating;
        this.status = status;
    }

    public CarBriefInfo() {
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarRating() {
        return carRating;
    }

    public void setCarRating(String carRating) {
        this.carRating = carRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(String serviceRating) {
        this.serviceRating = serviceRating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

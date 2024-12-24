package com.backend.models.table;

import com.google.gson.annotations.Expose;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Booking {

    @Expose
    private String bookingId;

    @Expose
    private String bookingDateTime;

    @Expose
    private String carId;

    @Expose
    private String carModel;

    @Expose
    private String carImageUrl;

//    @Expose
    private String clientId;

//    @Expose
    private String status;

//    @Expose
    private String dropOffDateTime;

//    @Expose
    private String dropOffLocationId;

//    @Expose
    private String pickupDateTime;

//   @Expose
    private String pickupLocationId;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("bookingId")
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    @DynamoDbAttribute("bookingDateTime")
    public String getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(String bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    @DynamoDbAttribute("carId")
    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    @DynamoDbAttribute("carModel")
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @DynamoDbAttribute("carImageUrl")
    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }

    @DynamoDbAttribute("clientId")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @DynamoDbAttribute("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @DynamoDbAttribute("dropOffDateTime")
    public String getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(String dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    @DynamoDbAttribute("dropOffLocationId")
    public String getDropOffLocationId() {
        return dropOffLocationId;
    }

    public void setDropOffLocationId(String dropOffLocationId) {
        this.dropOffLocationId = dropOffLocationId;
    }

    @DynamoDbAttribute("pickupDateTime")
    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public void setPickupDateTime(String pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    @DynamoDbAttribute("pickupLocationId")
    public String getPickupLocationId() {
        return pickupLocationId;
    }

    public void setPickupLocationId(String pickupLocationId) {
        this.pickupLocationId = pickupLocationId;
    }

}
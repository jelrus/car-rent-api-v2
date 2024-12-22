package com.backend.models.dto.request;

import com.google.gson.annotations.Expose;

/**
 *  Request to book a car
 */
public class BookCarRequest {

    @Expose
    private String carId;

    @Expose
    private String clientId;

    @Expose
    private String dropOffDateTime;

    @Expose
    private String dropOffLocationId;

    @Expose
    private String pickupDateTime;

    @Expose
    private String pickupLocationId;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(String dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public String getDropOffLocationId() {
        return dropOffLocationId;
    }

    public void setDropOffLocationId(String dropOffLocationId) {
        this.dropOffLocationId = dropOffLocationId;
    }

    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public void setPickupDateTime(String pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public String getPickupLocationId() {
        return pickupLocationId;
    }

    public void setPickupLocationId(String pickupLocationId) {
        this.pickupLocationId = pickupLocationId;
    }

}

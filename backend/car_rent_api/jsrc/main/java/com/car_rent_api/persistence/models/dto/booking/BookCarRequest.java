package com.car_rent_api.persistence.models.dto.booking;

import com.google.gson.annotations.Expose;

public class BookCarRequest {

    @Expose
    private String carId;

    @Expose
    private String clientId;

    @Expose
    private String pickupDateTime;

    @Expose
    private String dropOffDateTime;

    @Expose
    private String pickupLocationId;

    @Expose
    private String dropOffLocationId;

    public BookCarRequest() {}

    public String getCarId() {
        return carId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public String getDropOffDateTime() {
        return dropOffDateTime;
    }

    public String getPickupLocationId() {
        return pickupLocationId;
    }

    public String getDropOffLocationId() {
        return dropOffLocationId;
    }

    public static Builder builder() {
       return new BookCarRequest().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setCarId(String carId) {
            BookCarRequest.this.carId = carId;
            return this;
        }

        public Builder setClientId(String clientId) {
            BookCarRequest.this.clientId = clientId;
            return this;
        }

        public Builder setPickupDateTime(String pickupDateTime) {
            BookCarRequest.this.pickupDateTime = pickupDateTime;
            return this;
        }

        public Builder setDropOffDateTime(String dropOffDateTime) {
            BookCarRequest.this.dropOffDateTime = dropOffDateTime;
            return this;
        }

        public Builder setPickupLocationId(String pickupLocationId) {
            BookCarRequest.this.pickupLocationId = pickupLocationId;
            return this;
        }

        public Builder setDropOffLocationId(String dropOffLocationId) {
            BookCarRequest.this.dropOffLocationId = dropOffLocationId;
            return this;
        }

        public BookCarRequest build() {
            return BookCarRequest.this;
        }
    }
}
package com.car_rent_api.persistence.models.dto.booking;

import com.car_rent_api.utils.components.StringDateConverter;
import com.google.gson.annotations.Expose;

public class BookCarEditRequest {

    @Expose
    private String carId;

    @Expose
    private String pickupDateTime;

    @Expose
    private String dropOffDateTime;

    @Expose
    private String pickupLocationId;

    @Expose
    private String dropOffLocationId;

    public BookCarEditRequest() {}

    public String getCarId() {
        return carId;
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
        return new BookCarEditRequest().new Builder();
    }
    public Builder toBuilder() {
        return this.new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder carId(String carId) {
            BookCarEditRequest.this.carId = carId;
            return this;
        }

        public Builder pickupDateTime(String pickupDateTime) {
            BookCarEditRequest.this.pickupDateTime = StringDateConverter.adjustToISO8601DateTime(pickupDateTime);
            return this;
        }

        public Builder dropOffDateTime(String dropOffDateTime) {
            BookCarEditRequest.this.dropOffDateTime =  StringDateConverter.adjustToISO8601DateTime(dropOffDateTime);
            return this;
        }

        public Builder pickupLocationId(String pickupLocationId) {
            BookCarEditRequest.this.pickupLocationId = pickupLocationId;
            return this;
        }

        public Builder dropOffLocationId(String dropOffLocationId) {
            BookCarEditRequest.this.dropOffLocationId = dropOffLocationId;
            return this;
        }

        public BookCarEditRequest build() {
            return BookCarEditRequest.this;
        }
    }
}
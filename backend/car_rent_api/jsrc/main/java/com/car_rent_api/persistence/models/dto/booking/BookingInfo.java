package com.car_rent_api.persistence.models.dto.booking;

import com.car_rent_api.config.TableKeys;
import com.car_rent_api.utils.components.StringDateConverter;
import com.google.gson.annotations.Expose;

public class BookingInfo {

    @Expose
    private String bookingId;

    @Expose
    private String carId;

    @Expose
    private String bookingStatus;

    @Expose
    private String pickupLocationId;

    @Expose
    private String pickupDateTime;

    @Expose
    private String dropOffLocationId;

    @Expose
    private String dropOffDateTime;

    @Expose
    private String carImageUrl;

    @Expose
    private String carModel;

    @Expose
    private String orderDetails;

    public BookingInfo() {}

    public String getBookingId() {
        return bookingId;
    }

    public String getCarId() {
        return carId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public String getPickupLocationId() {
        return pickupLocationId;
    }

    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public String getDropOffLocationId() {
        return dropOffLocationId;
    }

    public String getDropOffDateTime() {
        return dropOffDateTime;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public static Builder builder() {
        return new BookingInfo().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder bookingId(String bookingId) {
            BookingInfo.this.bookingId = bookingId.replace(TableKeys.FEEDBACK_SK_PREFIX, "");
            return this;
        }

        public Builder carId(String carId) {
            BookingInfo.this.carId = carId;
            return this;
        }

        public Builder bookingStatus(String bookingStatus) {
            BookingInfo.this.bookingStatus = bookingStatus;
            return this;
        }

        public Builder pickupLocationId(String pickupLocationId) {
            BookingInfo.this.pickupLocationId = pickupLocationId;
            return this;
        }

        public Builder dropOffLocationId(String dropOffLocationId) {
            BookingInfo.this.dropOffLocationId = dropOffLocationId;
            return this;
        }

        public Builder pickupDateTime(String pickupDateTime) {
            BookingInfo.this.pickupDateTime = StringDateConverter.toISO8601DateTimeEdit(pickupDateTime);
            return this;
        }

        public Builder dropOffDateTime(String dropOffDateTime) {
            BookingInfo.this.dropOffDateTime = StringDateConverter.toISO8601DateTimeEdit(dropOffDateTime);
            return this;
        }

        public Builder carImageUrl(String carImageUrl) {
            BookingInfo.this.carImageUrl = carImageUrl;
            return this;
        }

        public Builder carModel(String carModel) {
            BookingInfo.this.carModel = carModel;
            return this;
        }

        public Builder orderDetails(String orderDetails) {
            BookingInfo.this.orderDetails = orderDetails;
            return this;
        }

        public BookingInfo build() {
            return BookingInfo.this;
        }
    }
}
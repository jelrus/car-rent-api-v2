package com.car_rent_api.persistence.models.dto.booking;

import com.car_rent_api.config.TableKeys;
import com.google.gson.annotations.Expose;

public class BookingInfo {

    @Expose
    private String bookingId;

    @Expose
    private String bookingStatus;

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

    public String getBookingStatus() {
        return bookingStatus;
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

        public Builder bookingStatus(String bookingStatus) {
            BookingInfo.this.bookingStatus = bookingStatus;
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
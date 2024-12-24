package com.backend.models.dto.response.booking;

import java.util.List;

public class BookingsResponse {

    private List<BookingInfo> content;

    public BookingsResponse() {}

    public List<BookingInfo> getContent() {
        return content;
    }

    public void setContent(List<BookingInfo> content) {
        this.content = content;
    }

    public enum BookingStatus {
        RESERVED,
        RESERVED_BY_SUPPORT_AGENT,
        SERVICE_STARTED,
        SERVICE_PROVIDED,
        BOOKING_FINISHED,
        CANCELLED
    }

    public static class BookingInfo {
        private String bookingId;
        private BookingStatus bookingStatus;
        private String carImageUrl;
        private String carModel;
        private String orderDetails;

        public BookingInfo() {
        }

        public String getBookingId() {
            return bookingId;
        }

        public void setBookingId(String bookingId) {
            this.bookingId = bookingId;
        }

        public BookingStatus getBookingStatus() {
            return bookingStatus;
        }

        public void setBookingStatus(BookingStatus bookingStatus) {
            this.bookingStatus = bookingStatus;
        }

        public String getCarImageUrl() {
            return carImageUrl;
        }

        public void setCarImageUrl(String carImageUrl) {
            this.carImageUrl = carImageUrl;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public String getOrderDetails() {
            return orderDetails;
        }

        public void setOrderDetails(String orderDetails) {
            this.orderDetails = orderDetails;
        }
    }
}
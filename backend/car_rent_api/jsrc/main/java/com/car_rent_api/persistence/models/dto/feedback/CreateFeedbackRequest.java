package com.car_rent_api.persistence.models.dto.feedback;

import com.google.gson.annotations.Expose;

public class CreateFeedbackRequest {

    @Expose
    private String bookingId;

    @Expose
    private String carId;

    @Expose
    private String clientId;

    @Expose
    private String feedbackText;

    @Expose
    private String rating;

    public CreateFeedbackRequest() {
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCarId() {
        return carId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public String getRating() {
        return rating;
    }

    public static Builder builder() {
        return new CreateFeedbackRequest().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setBookingId(String bookingId) {
            CreateFeedbackRequest.this.bookingId = bookingId;
            return this;
        }

        public Builder setCarId(String carId) {
            CreateFeedbackRequest.this.carId = carId;
            return this;
        }

        public Builder setClientId(String clientId) {
            CreateFeedbackRequest.this.clientId = clientId;
            return this;
        }

        public Builder setFeedbackText(String feedbackText) {
            CreateFeedbackRequest.this.feedbackText = feedbackText;
            return this;
        }

        public Builder setRating(String rating) {
            CreateFeedbackRequest.this.rating = rating;
            return this;
        }

        public CreateFeedbackRequest build() {
            return CreateFeedbackRequest.this;
        }
    }
}
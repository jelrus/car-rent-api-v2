package com.car_rent_api.persistence.models.entity;

import com.car_rent_api.config.TableKeys;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
public class Feedback {

    private String pkId;
    private String skId;
    private String carId;
    private String clientId;
    private String bookingId;
    private String date;
    private String rentalExperience;
    private String text;

    public Feedback() {}

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK_ID")
    public String getPkId() {
        return pkId;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SK_ID")
    public String getSkId() {
        return skId;
    }

    @DynamoDbAttribute("FEEDBACK#CAR_ID")
    public String getCarId() {
        return carId;
    }

    @DynamoDbAttribute("FEEDBACK#CLIENT_ID")
    public String getClientId() {
        return clientId;
    }

    @DynamoDbAttribute("FEEDBACK#BOOKING_ID")
    public String getBookingId() {
        return bookingId;
    }

    @DynamoDbSecondarySortKey(indexNames = TableKeys.FEEDBACK_DATE_IDX)
    @DynamoDbAttribute("FEEDBACK#DATE")
    public String getDate() {
        return date;
    }

    @DynamoDbSecondarySortKey(indexNames = TableKeys.FEEDBACK_RENTAL_EXPERIENCE_IDX)
    @DynamoDbAttribute("FEEDBACK#RENTAL_EXPERIENCE")
    public String getRentalExperience() {
        return rentalExperience;
    }

    @DynamoDbAttribute("FEEDBACK#TEXT")
    public String getText() {
        return text;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public void setSkId(String skId) {
        this.skId = skId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRentalExperience(String rentalExperience) {
        this.rentalExperience = rentalExperience;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Builder builder() {
        return new Feedback().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder pkId() {
            Feedback.this.pkId = TableKeys.FEEDBACK_PK;
            return this;
        }

        public Builder skId(String id) {
            Feedback.this.skId = TableKeys.FEEDBACK_SK_PREFIX + id;
            return this;
        }

        public Builder carId(String id) {
            Feedback.this.carId = id;
            return this;
        }

        public Builder clientId(String id) {
            Feedback.this.clientId = id;
            return this;
        }

        public Builder bookingId(String bookingId) {
            Feedback.this.bookingId = bookingId;
            return this;
        }

        public Builder date(String date) {
            Feedback.this.date = date;
            return this;
        }

        public Builder rentalExperience(String rentalExperience) {
            Feedback.this.rentalExperience = rentalExperience;
            return this;
        }

        public Builder text(String text) {
            Feedback.this.text = text;
            return this;
        }

        public Feedback build() {
            return Feedback.this;
        }
    }
}
package com.car_rent_api.persistence.models.dto.booking;

import com.google.gson.annotations.Expose;

public class BookCarServiceProvidedRequest {

    @Expose
    private int mileage;

    public BookCarServiceProvidedRequest() {}

    public int getMileage() {
        return mileage;
    }

    public static Builder builder() {
        return new BookCarServiceProvidedRequest().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder mileage(int mileage) {
            BookCarServiceProvidedRequest.this.mileage = mileage;
            return this;
        }

        public BookCarServiceProvidedRequest build() {
            return BookCarServiceProvidedRequest.this;
        }
    }
}
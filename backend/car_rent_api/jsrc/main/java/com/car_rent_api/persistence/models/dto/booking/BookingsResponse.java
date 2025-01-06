package com.car_rent_api.persistence.models.dto.booking;

import com.google.gson.annotations.Expose;

import java.util.List;

public class BookingsResponse {

    @Expose
    private List<BookingInfo> content;

    public BookingsResponse() {}

    public List<BookingInfo> getContent() {
        return content;
    }

    public static Builder builder() {
        return new BookingsResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder content(List<BookingInfo> content) {
            BookingsResponse.this.content = content;
            return this;
        }

        public BookingsResponse build() {
            return BookingsResponse.this;
        }
    }
}
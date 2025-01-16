package com.car_rent_api.persistence.models.dto.booking;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class BookingsResponse {

    @Expose
    private Set<BookingInfo> content;

    public BookingsResponse() {}

    public Set<BookingInfo> getContent() {
        return content;
    }

    public static Builder builder() {
        return new BookingsResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder content(Set<BookingInfo> content) {
            BookingsResponse.this.content = content;
            return this;
        }

        public BookingsResponse build() {
            return BookingsResponse.this;
        }
    }
}
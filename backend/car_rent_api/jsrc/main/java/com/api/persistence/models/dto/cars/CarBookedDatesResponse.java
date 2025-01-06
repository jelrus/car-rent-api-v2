package com.api.persistence.models.dto.cars;

import com.google.gson.annotations.Expose;

import java.util.List;

public class CarBookedDatesResponse {

    @Expose
    private List<String> content;

    public List<String> getContent() {
        return content;
    }

    public static Builder builder() {
        return new CarBookedDatesResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder content(List<String> content) {
            CarBookedDatesResponse.this.content = content;
            return this;
        }

        public CarBookedDatesResponse build() {
            return CarBookedDatesResponse.this;
        }
    }
}
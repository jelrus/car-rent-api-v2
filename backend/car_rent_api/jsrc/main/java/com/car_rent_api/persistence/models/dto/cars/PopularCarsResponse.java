package com.car_rent_api.persistence.models.dto.cars;

import com.google.gson.annotations.Expose;

import java.util.List;

public class PopularCarsResponse {

    @Expose
    private List<CarBriefInfo> content;

    public PopularCarsResponse() {}

    public List<CarBriefInfo> getContent() {
        return content;
    }

    public static Builder builder() {
        return new PopularCarsResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder content(List<CarBriefInfo> content) {
            PopularCarsResponse.this.content = content;
            return this;
        }

        public PopularCarsResponse build() {
            return PopularCarsResponse.this;
        }
    }
}
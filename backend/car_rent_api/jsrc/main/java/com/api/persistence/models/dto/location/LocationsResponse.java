package com.api.persistence.models.dto.location;

import com.google.gson.annotations.Expose;

import java.util.List;

public class LocationsResponse {

    @Expose
    private List<LocationInfo> content;

    public LocationsResponse() {}

    public List<LocationInfo> getContent() {
        return content;
    }

    public static Builder builder() {
        return new LocationsResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder content(List<LocationInfo> content) {
            LocationsResponse.this.content = content;
            return this;
        }

        public LocationsResponse build() {
            return LocationsResponse.this;
        }
    }
}
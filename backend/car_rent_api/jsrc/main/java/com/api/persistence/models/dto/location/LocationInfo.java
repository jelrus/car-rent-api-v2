package com.api.persistence.models.dto.location;

import com.api.config.TableKeys;
import com.google.gson.annotations.Expose;

public class LocationInfo {

    @Expose
    private String locationId;

    @Expose
    private String locationAddress;

    @Expose
    private String locationName;

    @Expose
    private String locationImageUrl;

    public LocationInfo() {}

    public String getLocationId() {
        return locationId;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getLocationImageUrl() {
        return locationImageUrl;
    }

    public static Builder builder() {
        return new LocationInfo().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder locationId(String locationId) {
            LocationInfo.this.locationId = locationId.replace(TableKeys.LOCATION_SK_PREFIX, "");
            return this;
        }

        public Builder locationAddress(String locationAddress) {
            LocationInfo.this.locationAddress = locationAddress;
            return this;
        }

        public Builder locationName(String locationName) {
            LocationInfo.this.locationName = locationName;
            return this;
        }

        public Builder locationImageUrl(String locationImageUrl) {
            LocationInfo.this.locationImageUrl = locationImageUrl;
            return this;
        }

        public LocationInfo build() {
            return LocationInfo.this;
        }
    }
}
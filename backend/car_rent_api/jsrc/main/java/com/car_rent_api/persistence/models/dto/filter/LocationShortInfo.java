package com.car_rent_api.persistence.models.dto.filter;

import com.car_rent_api.config.TableKeys;
import com.google.gson.annotations.Expose;

public class LocationShortInfo {

    @Expose
    private String locationId;

    @Expose
    private String locationName;

    public LocationShortInfo() {}

    public String getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public static Builder builder() {
        return new LocationShortInfo().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder locationId(String locationId) {
            LocationShortInfo.this.locationId = locationId.replace(TableKeys.LOCATION_SK_PREFIX, "");
            return this;
        }

        public Builder locationName(String locationName) {
            LocationShortInfo.this.locationName = locationName;
            return this;
        }

        public LocationShortInfo build() {
            return LocationShortInfo.this;
        }
    }
}
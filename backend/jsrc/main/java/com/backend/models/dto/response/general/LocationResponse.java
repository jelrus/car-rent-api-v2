package com.backend.models.dto.response.general;

import java.util.List;

public class LocationResponse {

    private List<LocationInfo> content;

    public List<LocationInfo> getContent() {
        return content;
    }

    public void setContent(List<LocationInfo> content) {
        this.content = content;
    }

    public static class LocationInfo {

        private String locationAddress;
        private String locationId;
        private String locationImageUrl;
        private String locationName;

        public LocationInfo() {}

        public String getLocationAddress() {
            return locationAddress;
        }

        public void setLocationAddress(String locationAddress) {
            this.locationAddress = locationAddress;
        }

        public String getLocationId() {
            return locationId;
        }

        public void setLocationId(String locationId) {
            this.locationId = locationId;
        }

        public String getLocationImageUrl() {
            return locationImageUrl;
        }

        public void setLocationImageUrl(String locationImageUrl) {
            this.locationImageUrl = locationImageUrl;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }
    }
}
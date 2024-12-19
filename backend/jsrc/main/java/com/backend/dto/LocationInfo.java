package com.backend.dto;

public class LocationInfo {
   private String locationName;
   private String locationAddress;
   private String locationId;
   private String locationImageUrl;

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

    public LocationInfo() {
    }

    public LocationInfo(String locationAddress, String locationId, String locationImageUrl, String locationName) {
        this.locationAddress = locationAddress;
        this.locationId = locationId;
        this.locationImageUrl = locationImageUrl;
        this.locationName = locationName;
    }
}

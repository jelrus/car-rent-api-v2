package com.backend.models.table;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Location {

    private String locationId;
    private String locationAddress;
    private String locationImageUrl;
    private String locationName;

    public Location() {}

    @DynamoDbPartitionKey
    @DynamoDbAttribute("locationId")
    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @DynamoDbAttribute("locationAddress")
    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    @DynamoDbAttribute("locationImageUrl")
    public String getLocationImageUrl() {
        return locationImageUrl;
    }

    public void setLocationImageUrl(String locationImageUrl) {
        this.locationImageUrl = locationImageUrl;
    }

    @DynamoDbAttribute("locationName")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
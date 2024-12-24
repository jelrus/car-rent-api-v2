package com.backend.models.table;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class LocationEntity {

    private String locationId;
    private String locationAddress;
    private String locationImageUrl;
    private String locationName;



    @DynamoDbPartitionKey
    @DynamoDbAttribute("locationId")
    public String getLocationId() {
        return locationId;
    }

    @DynamoDbAttribute("locationAddress")
    public String getLocationAddress() {
        return locationAddress;
    }

    @DynamoDbAttribute("locationImageUrl")
    public String getLocationImageUrl() {
        return locationImageUrl;
    }

    @DynamoDbAttribute("locationName")
    public String getLocationName() {
        return locationName;
    }

    public LocationEntity() {
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public void setLocationImageUrl(String locationImageUrl) {
        this.locationImageUrl = locationImageUrl;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return "LocationEntity{" +
                "locationAddress='" + locationAddress + '\'' +
                ", locationId='" + locationId + '\'' +
                ", locationImageUrl='" + locationImageUrl + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
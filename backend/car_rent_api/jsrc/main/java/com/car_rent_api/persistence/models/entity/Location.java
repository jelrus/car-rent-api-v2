package com.car_rent_api.persistence.models.entity;

import com.car_rent_api.config.TableKeys;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class Location {

    private String pkId;
    private String skId;
    private String name;
    private String address;
    private String imageUrl;
    private String supportAgentId;

    public Location() {}

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK_ID")
    public String getPkId() {
        return pkId;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SK_ID")
    public String getSkId() {
        return skId;
    }

    @DynamoDbAttribute("LOCATION#NAME")
    public String getName() {
        return name;
    }

    @DynamoDbAttribute("LOCATION#ADDRESS")
    public String getAddress() {
        return address;
    }

    @DynamoDbAttribute("LOCATION#IMAGE_URL")
    public String getImageUrl() {
        return imageUrl;
    }

    @DynamoDbAttribute("LOCATION#SUPPORT_AGENT_ID")
    public String getSupportAgentId() {
        return supportAgentId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public void setSkId(String skId) {
        this.skId = skId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSupportAgentId(String supportAgentId) {
        this.supportAgentId = supportAgentId;
    }

    public static Builder builder() {
        return new Location().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder pkId() {
            Location.this.pkId = TableKeys.LOCATION_PK;
            return this;
        }

        public Builder skId(String id) {
            Location.this.skId = TableKeys.LOCATION_SK_PREFIX + id;
            return this;
        }

        public Builder name(String name) {
            Location.this.name = name;
            return this;
        }

        public Builder address(String address) {
            Location.this.address = address;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            Location.this.imageUrl = imageUrl;
            return this;
        }

        public Builder supportAgentId(String id) {
            Location.this.supportAgentId = id;
            return this;
        }

        public Location build() {
            return Location.this;
        }
    }
}
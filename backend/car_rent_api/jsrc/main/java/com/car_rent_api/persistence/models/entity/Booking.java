package com.car_rent_api.persistence.models.entity;

import com.car_rent_api.config.TableKeys;
import com.car_rent_api.persistence.models.entity.types.BookingStatus;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
public class Booking {

    private String pkId;
    private String skId;
    private String orderDetails;
    private BookingStatus status;
    private String clientId;
    private String madeBy;
    private String carId;
    private String createdAt;
    private String lockedFrom;
    private String pickupDateTime;
    private String dropOffDateTime;
    private String pickupLocationId;
    private String dropOffLocationId;
    private String supportAgentId;
    private Integer carMileageStart;
    private Integer carMileageEnd;

    public Booking() {}

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

    @DynamoDbAttribute("BOOKING#ORDER_DETAILS")
    public String getOrderDetails() {
        return orderDetails;
    }

    @DynamoDbAttribute("BOOKING#STATUS")
    public BookingStatus getStatus() {
        return status;
    }

    @DynamoDbAttribute("BOOKING#MADE_BY")
    public String getMadeBy() {
        return madeBy;
    }

    @DynamoDbAttribute("BOOKING#CLIENT_ID")
    public String getClientId() {
        return clientId;
    }

    @DynamoDbAttribute("BOOKING#CAR_ID")
    public String getCarId() {
        return carId;
    }

    @DynamoDbSecondarySortKey(indexNames = "BOOKING_CREATED_AT_IDX")
    @DynamoDbAttribute("BOOKING#CREATED_AT")
    public String getCreatedAt() {
        return createdAt;
    }

    @DynamoDbAttribute("BOOKING#LOCKED_FROM")
    public String getLockedFrom() {
        return lockedFrom;
    }

    @DynamoDbAttribute("BOOKING#PICKUP_DATE_TIME")
    public String getPickupDateTime() {
        return pickupDateTime;
    }

    @DynamoDbAttribute("BOOKING#DROPOFF_DATE_TIME")
    public String getDropOffDateTime() {
        return dropOffDateTime;
    }

    @DynamoDbAttribute("BOOKING#PICKUP_LOCATION_ID")
    public String getPickupLocationId() {
        return pickupLocationId;
    }

    @DynamoDbAttribute("BOOKING#DROPOFF_LOCATION_ID")
    public String getDropOffLocationId() {
        return dropOffLocationId;
    }

    @DynamoDbAttribute("BOOKING#SUPPORT_AGENT_ID")
    public String getSupportAgentId() {
        return supportAgentId;
    }

    @DynamoDbAttribute("BOOKING#CAR_MILEAGE_START")
    public Integer getCarMileageStart() {
        return carMileageStart;
    }

    @DynamoDbAttribute("BOOKING#CAR_MILEAGE_END")
    public Integer getCarMileageEnd() {
        return carMileageEnd;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public void setSkId(String skId) {
        this.skId = skId;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setLockedFrom(String lockedFrom) {
        this.lockedFrom = lockedFrom;
    }

    public void setPickupDateTime(String pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public void setDropOffDateTime(String dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public void setPickupLocationId(String pickupLocationId) {
        this.pickupLocationId = pickupLocationId;
    }

    public void setDropOffLocationId(String dropOffLocationId) {
        this.dropOffLocationId = dropOffLocationId;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    public void setSupportAgentId(String supportAgentId) {
        this.supportAgentId = supportAgentId;
    }

    public void setCarMileageStart(Integer carMileageStart) {
        this.carMileageStart = carMileageStart;
    }

    public void setCarMileageEnd(Integer carMileageEnd) {
        this.carMileageEnd = carMileageEnd;
    }

    public static Builder builder() {
        return new Booking().new Builder();
    }

    public Builder toBuilder() {
        return this.new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder pkId() {
            Booking.this.pkId = TableKeys.BOOKING_PK;
            return this;
        }

        public Builder skId(String id) {
            Booking.this.skId = TableKeys.BOOKING_SK_PREFIX + id;
            return this;
        }

        public Builder orderDetails(String orderDetails) {
            Booking.this.orderDetails = orderDetails;
            return this;
        }

        public Builder status(BookingStatus status) {
            Booking.this.status = status;
            return this;
        }

        public Builder madeBy(String madeBy) {
            Booking.this.madeBy = madeBy;
            return this;
        }

        public Builder clientId(String clientId) {
            Booking.this.clientId = clientId;
            return this;
        }

        public Builder carId(String carId) {
            Booking.this.carId = carId;
            return this;
        }

        public Builder createdAt(String createdAt) {
            Booking.this.createdAt = createdAt;
            return this;
        }

        public Builder lockedFrom(String lockedFrom) {
            Booking.this.lockedFrom = lockedFrom;
            return this;
        }

        public Builder pickupDateTime(String pickupDateTime) {
            Booking.this.pickupDateTime = pickupDateTime;
            return this;
        }

        public Builder dropOffDateTime(String dropOffDateTime) {
            Booking.this.dropOffDateTime = dropOffDateTime;
            return this;
        }

        public Builder pickupLocationId(String pickupLocationId) {
            Booking.this.pickupLocationId = pickupLocationId;
            return this;
        }

        public Builder dropOffLocationId(String dropOffLocationId) {
            Booking.this.dropOffLocationId = dropOffLocationId;
            return this;
        }

        public Builder supportAgentId(String supportAgentId) {
            Booking.this.supportAgentId = supportAgentId;
            return this;
        }

        public Builder carMileageStart(Integer carMileageStart) {
            Booking.this.carMileageStart = carMileageStart;
            return this;
        }

        public Builder carMileageEnd(Integer carMileageEnd) {
            Booking.this.carMileageEnd = carMileageEnd;
            return this;
        }

        public Booking build() {
            return Booking.this;
        }
    }
}
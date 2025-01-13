package com.car_rent_api.persistence.models.dto.cars;

import com.car_rent_api.config.TableKeys;
import com.google.gson.annotations.Expose;

import java.util.List;

public class CarBriefInfo {

    @Expose
    private String carId;

    @Expose
    private String imageUrl;

    @Expose
    private String location;

    @Expose
    private String pickupLocationId;

    @Expose
    private List<String> dropOffLocationsIds;

    @Expose
    private String model;

    @Expose
    private String pricePerDay;

    @Expose
    private String rentalExperience;

    @Expose
    private String status;

    public CarBriefInfo() {}

    public String getCarId() {
        return carId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getPickupLocationId() {
        return pickupLocationId;
    }

    public List<String> getDropOffLocationsIds() {
        return dropOffLocationsIds;
    }

    public String getModel() {
        return model;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public String getRentalExperience() {
        return rentalExperience;
    }

    public String getStatus() {
        return status;
    }

    public static Builder builder() {
        return new CarBriefInfo().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder carId(String carId) {
            CarBriefInfo.this.carId = carId.replace(TableKeys.CAR_SK_PREFIX, "");
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            CarBriefInfo.this.imageUrl = imageUrl;
            return this;
        }

        public Builder location(String location) {
            CarBriefInfo.this.location = location;
            return this;
        }

        public Builder pickupLocationId(String pickupLocationId) {
            CarBriefInfo.this.pickupLocationId = pickupLocationId;
            return this;
        }

        public Builder dropOffLocationsIds(List<String> dropOffLocationsIds) {
            CarBriefInfo.this.dropOffLocationsIds = dropOffLocationsIds;
            return this;
        }

        public Builder model(String model) {
            CarBriefInfo.this.model = model;
            return this;
        }

        public Builder pricePerDay(String pricePerDay) {
            CarBriefInfo.this.pricePerDay = pricePerDay;
            return this;
        }

        public Builder rentalExperience(String rentalExperience) {
            CarBriefInfo.this.rentalExperience = rentalExperience;
            return this;
        }

        public Builder status(String status) {
            CarBriefInfo.this.status = status;
            return this;
        }

        public CarBriefInfo build() {
            return CarBriefInfo.this;
        }
    }
}
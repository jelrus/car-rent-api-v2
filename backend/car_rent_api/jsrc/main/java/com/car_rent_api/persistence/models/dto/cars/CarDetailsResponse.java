package com.car_rent_api.persistence.models.dto.cars;

import com.car_rent_api.config.TableKeys;
import com.google.gson.annotations.Expose;

import java.util.List;

public class CarDetailsResponse {

    @Expose
    private String carId;

    @Expose
    private String rentalExperience;

    @Expose
    private String climateControlOption;

    @Expose
    private String engineCapacity;

    @Expose
    private String fuelConsumption;

    @Expose
    private String fuelType;

    @Expose
    private String gearBoxType;

    @Expose
    private List<String> images;

    @Expose
    private String location;

    @Expose
    private String model;

    @Expose
    private String passengerCapacity;

    @Expose
    private String pricePerDay;

    @Expose
    private String status;

    public CarDetailsResponse() {}

    public String getCarId() {
        return carId;
    }

    public String getRentalExperience() {
        return rentalExperience;
    }

    public String getClimateControlOption() {
        return climateControlOption;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public String getFuelConsumption() {
        return fuelConsumption;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getGearBoxType() {
        return gearBoxType;
    }

    public List<String> getImages() {
        return images;
    }

    public String getLocation() {
        return location;
    }

    public String getModel() {
        return model;
    }

    public String getPassengerCapacity() {
        return passengerCapacity;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public String getStatus() {
        return status;
    }

    public static Builder builder() {
        return new CarDetailsResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder carId(String carId) {
            CarDetailsResponse.this.carId = carId.replace(TableKeys.CAR_SK_PREFIX, "");
            return this;
        }

        public Builder rentalExperience(String rentalExperience) {
            CarDetailsResponse.this.rentalExperience = rentalExperience;
            return this;
        }

        public Builder climateControlOption(String climateControlOption) {
            CarDetailsResponse.this.climateControlOption = climateControlOption;
            return this;
        }

        public Builder engineCapacity(String engineCapacity) {
            CarDetailsResponse.this.engineCapacity = engineCapacity;
            return this;
        }

        public Builder fuelConsumption(String fuelConsumption) {
            CarDetailsResponse.this.fuelConsumption = fuelConsumption;
            return this;
        }

        public Builder fuelType(String fuelType) {
            CarDetailsResponse.this.fuelType = fuelType;
            return this;
        }

        public Builder gearBoxType(String gearBoxType) {
            CarDetailsResponse.this.gearBoxType = gearBoxType;
            return this;
        }

        public Builder images(List<String> images) {
            CarDetailsResponse.this.images = images;
            return this;
        }

        public Builder location(String location) {
            CarDetailsResponse.this.location = location;
            return this;
        }

        public Builder model(String model) {
            CarDetailsResponse.this.model = model;
            return this;
        }

        public Builder passengerCapacity(String passengerCapacity) {
            CarDetailsResponse.this.passengerCapacity = passengerCapacity;
            return this;
        }

        public Builder pricePerDay(String pricePerDay) {
            CarDetailsResponse.this.pricePerDay = pricePerDay;
            return this;
        }

        public Builder status(String status) {
            CarDetailsResponse.this.status = status;
            return this;
        }

        public CarDetailsResponse build() {
            return CarDetailsResponse.this;
        }
    }
}
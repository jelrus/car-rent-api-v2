package com.car_rent_api.persistence.models.entity;

import com.car_rent_api.config.TableKeys;
import com.car_rent_api.persistence.models.entity.types.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.util.List;

@DynamoDbBean
public class Car {

    private String pkId;
    private String skId;
    private String model;
    private String numbers;
    private CarStatus status;
    private Integer pricePerDay;
    private CarCategory category;
    private String imageUrl;
    private List<String> images;
    private String pickupLocationId;
    private List<String> dropOffLocationsIds;
    private String engineCapacity;
    private String fuelConsumption;
    private CarFuelType fuelType;
    private CarClimateControlOption climateControlOption;
    private CarGearBoxType gearBoxType;
    private String passengerCapacity;
    private String rentalExperience;
    private List<String> bookedDays;
    private Integer mileageTotal;

    public Car() {}

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

    @DynamoDbAttribute("CAR#MODEL")
    public String getModel() {
        return model;
    }

    @DynamoDbAttribute("CAR#NUMBERS")
    public String getNumbers() {
        return numbers;
    }

    @DynamoDbSecondarySortKey(indexNames = TableKeys.CAR_STATUS_IDX)
    @DynamoDbAttribute("CAR#STATUS")
    public CarStatus getStatus() {
        return status;
    }

    @DynamoDbAttribute("CAR#PRICE_PER_DAY")
    public Integer getPricePerDay() {
        return pricePerDay;
    }

    @DynamoDbAttribute("CAR#CATEGORY")
    public CarCategory getCategory() {
        return category;
    }

    @DynamoDbAttribute("CAR#IMAGE_URL")
    public String getImageUrl() {
        return imageUrl;
    }

    @DynamoDbAttribute("CAR#IMAGES")
    public List<String> getImages() {
        return images;
    }

    @DynamoDbAttribute("CAR#PICKUP_LOCATION_ID")
    public String getPickupLocationId() {
        return pickupLocationId;
    }

    @DynamoDbAttribute("CAR#DROPOFF_LOCATIONS_IDS")
    public List<String> getDropOffLocationsIds() {
        return dropOffLocationsIds;
    }

    @DynamoDbAttribute("CAR#ENGINE_CAPACITY")
    public String getEngineCapacity() {
        return engineCapacity;
    }

    @DynamoDbAttribute("CAR#FUEL_CONSUMPTION")
    public String getFuelConsumption() {
        return fuelConsumption;
    }

    @DynamoDbAttribute("CAR#FUEL_TYPE")
    public CarFuelType getFuelType() {
        return fuelType;
    }

    @DynamoDbAttribute("CAR#CLIMATE_CONTROL_OPTION")
    public CarClimateControlOption getClimateControlOption() {
        return climateControlOption;
    }

    @DynamoDbAttribute("CAR#GEAR_BOX_TYPE")
    public CarGearBoxType getGearBoxType() {
        return gearBoxType;
    }

    @DynamoDbAttribute("CAR#PASSENGER_CAPACITY")
    public String getPassengerCapacity() {
        return passengerCapacity;
    }

    @DynamoDbSecondarySortKey(indexNames = TableKeys.CAR_RENTAL_EXPERIENCE_IDX)
    @DynamoDbAttribute("CAR#RENTAL_EXPERIENCE")
    public String getRentalExperience() {
        return rentalExperience;
    }

    @DynamoDbAttribute("CAR#BOOKED_DAYS")
    public List<String> getBookedDays() {
        return bookedDays;
    }

    @DynamoDbAttribute("CAR#MILEAGE_TOTAL")
    public Integer getMileageTotal() {
        return mileageTotal;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public void setSkId(String skId) {
        this.skId = skId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public void setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setCategory(CarCategory category) {
        this.category = category;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setPickupLocationId(String pickupLocationId) {
        this.pickupLocationId = pickupLocationId;
    }

    public void setDropOffLocationsIds(List<String> dropOffLocationsIds) {
        this.dropOffLocationsIds = dropOffLocationsIds;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void setFuelConsumption(String fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public void setFuelType(CarFuelType fuelType) {
        this.fuelType = fuelType;
    }

    public void setClimateControlOption(CarClimateControlOption climateControlOption) {
        this.climateControlOption = climateControlOption;
    }

    public void setGearBoxType(CarGearBoxType gearBoxType) {
        this.gearBoxType = gearBoxType;
    }

    public void setPassengerCapacity(String passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public void setRentalExperience(String rentalExperience) {
        this.rentalExperience = rentalExperience;
    }

    public void setBookedDays(List<String> bookedDays) {
        this.bookedDays = bookedDays;
    }

    public void setMileageTotal(Integer mileageTotal) {
        this.mileageTotal = mileageTotal;
    }

    public Builder toBuilder() {
        return this.new Builder();
    }

    public static Builder builder() {
        return new Car().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder pkId() {
            Car.this.pkId = TableKeys.CAR_PK;
            return this;
        }

        public Builder skId(String id) {
            Car.this.skId = TableKeys.CAR_SK_PREFIX + id;
            return this;
        }

        public Builder model(String model) {
            Car.this.model = model;
            return this;
        }

        public Builder numbers(String numbers) {
            Car.this.numbers = numbers;
            return this;
        }

        public Builder status(CarStatus status) {
            Car.this.status = status;
            return this;
        }

        public Builder pricePerDay(Integer pricePerDay) {
            Car.this.pricePerDay = pricePerDay;
            return this;
        }

        public Builder category(CarCategory category) {
            Car.this.category = category;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            Car.this.imageUrl = imageUrl;
            return this;
        }

        public Builder images(List<String> images) {
            Car.this.images = images;
            return this;
        }

        public Builder pickUpLocation(String pickUpLocationId) {
            Car.this.pickupLocationId = pickUpLocationId;
            return this;
        }

        public Builder dropOffLocations(List<String> dropOffLocationsIds) {
            Car.this.dropOffLocationsIds = dropOffLocationsIds;
            return this;
        }

        public Builder engineCapacity(String engineCapacity) {
            Car.this.engineCapacity = engineCapacity;
            return this;
        }

        public Builder fuelConsumption(String fuelConsumption) {
            Car.this.fuelConsumption = fuelConsumption;
            return this;
        }

        public Builder fuelType(CarFuelType fuelType) {
            Car.this.fuelType = fuelType;
            return this;
        }

        public Builder climateControlOption(CarClimateControlOption climateControlOption) {
            Car.this.climateControlOption = climateControlOption;
            return this;
        }

        public Builder gearBoxType(CarGearBoxType gearBoxType) {
            Car.this.gearBoxType = gearBoxType;
            return this;
        }

        public Builder passengerCapacity(String passengerCapacity) {
            Car.this.passengerCapacity = passengerCapacity;
            return this;
        }

        public Builder rentalExperience(String rentalExperience) {
            Car.this.rentalExperience = rentalExperience;
            return this;
        }

        public Builder bookedDays(List<String> bookedDays) {
            Car.this.bookedDays = bookedDays;
            return this;
        }

        public Builder mileageTotal(Integer mileageTotal) {
            Car.this.mileageTotal = mileageTotal;
            return this;
        }

        public Car build() {
            return Car.this;
        }
    }
}
package com.car_rent_api.persistence.models.dto.filter;

import com.car_rent_api.config.TableKeys;
import com.google.gson.annotations.Expose;

public class CarShortInfo {

    @Expose
    private String carId;

    @Expose
    private String carModel;

    public CarShortInfo() {}

    public String getCarId() {
        return carId;
    }

    public String getCarModel() {
        return carModel;
    }

    public static Builder builder() {
        return new CarShortInfo().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder carId(String carId) {
            CarShortInfo.this.carId = carId.replace(TableKeys.CAR_SK_PREFIX, "");
            return this;
        }

        public Builder carModel(String carModel) {
            CarShortInfo.this.carModel = carModel;
            return this;
        }

        public CarShortInfo build() {
            return CarShortInfo.this;
        }
    }
}
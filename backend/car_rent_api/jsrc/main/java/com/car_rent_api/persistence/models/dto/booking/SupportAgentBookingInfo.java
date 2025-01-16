package com.car_rent_api.persistence.models.dto.booking;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class SupportAgentBookingInfo {

    @Expose
    private String bookingPeriodStart;

    @Expose
    private String bookingPeriodEnd;

    @Expose
    private String carModel;

    @Expose
    private String carNumbers;

    @Expose
    private String carMileageStart;

    @Expose
    private String carMileageEnd;

    @Expose
    private String madeBy;

    @Expose
    private String supportAgent;

    @Expose
    private String rentalExperience;

    public SupportAgentBookingInfo() {}

    public String getBookingPeriodStart() {
        return bookingPeriodStart;
    }

    public String getBookingPeriodEnd() {
        return bookingPeriodEnd;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarNumbers() {
        return carNumbers;
    }

    public String getCarMileageStart() {
        return carMileageStart;
    }

    public String getCarMileageEnd() {
        return carMileageEnd;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public String getSupportAgent() {
        return supportAgent;
    }

    public String getRentalExperience() {
        return rentalExperience;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SupportAgentBookingInfo that = (SupportAgentBookingInfo) object;
        return Objects.equals(bookingPeriodStart, that.bookingPeriodStart)
                && Objects.equals(bookingPeriodEnd, that.bookingPeriodEnd)
                && Objects.equals(carModel, that.carModel)
                && Objects.equals(carNumbers, that.carNumbers)
                && Objects.equals(carMileageStart, that.carMileageStart)
                && Objects.equals(carMileageEnd, that.carMileageEnd)
                && Objects.equals(madeBy, that.madeBy)
                && Objects.equals(supportAgent, that.supportAgent)
                && Objects.equals(rentalExperience, that.rentalExperience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingPeriodStart, bookingPeriodEnd, carModel, carNumbers, carMileageStart,
                carMileageEnd, madeBy, supportAgent, rentalExperience);
    }

    public static Builder builder() {
        return new SupportAgentBookingInfo().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder periodStart(String periodStart) {
            SupportAgentBookingInfo.this.bookingPeriodStart = periodStart;
            return this;
        }

        public Builder periodEnd(String periodEnd) {
            SupportAgentBookingInfo.this.bookingPeriodEnd = periodEnd;
            return this;
        }

        public Builder carModel(String carModel) {
            SupportAgentBookingInfo.this.carModel = carModel;
            return this;
        }

        public Builder carNumbers(String carNumbers) {
            SupportAgentBookingInfo.this.carNumbers = carNumbers;
            return this;
        }

        public Builder carMileageStart(String carMileageStart) {
            SupportAgentBookingInfo.this.carMileageStart = carMileageStart;
            return this;
        }

        public Builder carMileageEnd(String carMileageEnd) {
            SupportAgentBookingInfo.this.carMileageEnd = carMileageEnd;
            return this;
        }

        public Builder madeBy(String madeBy) {
            SupportAgentBookingInfo.this.madeBy = madeBy;
            return this;
        }

        public Builder supportAgent(String supportAgentUsername) {
            SupportAgentBookingInfo.this.supportAgent = supportAgentUsername;
            return this;
        }

        public Builder rentalExperience(String rentalExperience) {
            SupportAgentBookingInfo.this.rentalExperience = rentalExperience;
            return this;
        }

        public SupportAgentBookingInfo build() {
            return SupportAgentBookingInfo.this;
        }
    }
}
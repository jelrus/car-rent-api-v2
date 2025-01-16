package com.car_rent_api.persistence.models.report;

import com.car_rent_api.utils.components.StringDateConverter;

public class FilterReportRow {

    private String bookingPeriod;
    private String car;
    private String carNumbers;
    private Integer carMileageStart;
    private Integer carMileageEnd;
    private String madeBy;
    private String supportAgent;
    private Double rating;

    public FilterReportRow() {}

    public String getBookingPeriod() {
        return bookingPeriod;
    }

    public String getCar() {
        return car;
    }

    public String getCarNumbers() {
        return carNumbers;
    }

    public Integer getCarMileageStart() {
        return carMileageStart;
    }

    public Integer getCarMileageEnd() {
        return carMileageEnd;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public String getSupportAgent() {
        return supportAgent;
    }

    public Double getRating() {
        return rating;
    }

    public static Builder builder() {
        return new FilterReportRow().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder bookingPeriod(String pickupDate, String dropOffDate) {
            String adjustedPickupDate = StringDateConverter.adjustToISO8601DateTime(pickupDate);
            String adjustedDropOffDate = StringDateConverter.adjustToISO8601DateTime(dropOffDate);
            String activePickupDate = StringDateConverter.fromISO8601DateTimeToBookingActiveDate(adjustedPickupDate);
            String activeDropOffDate = StringDateConverter.fromISO8601DateTimeToBookingActiveDate(adjustedDropOffDate);
            FilterReportRow.this.bookingPeriod = activePickupDate + " - " + activeDropOffDate;
            return this;
        }

        public Builder car(String car) {
            FilterReportRow.this.car = car;
            return this;
        }

        public Builder carNumbers(String carNumbers) {
            FilterReportRow.this.carNumbers = carNumbers;
            return this;
        }

        public Builder carMileageStart(Integer carMileageStart) {
            FilterReportRow.this.carMileageStart = carMileageStart;
            return this;
        }

        public Builder carMileageEnd(Integer carMileageEnd) {
            FilterReportRow.this.carMileageEnd = carMileageEnd;
            return this;
        }

        public Builder madeBy(String madeBy) {
            FilterReportRow.this.madeBy = madeBy;
            return this;
        }

        public Builder supportAgent(String supportAgent) {
            FilterReportRow.this.supportAgent = supportAgent;
            return this;
        }

        public Builder rating(String rating) {
            FilterReportRow.this.rating = Double.parseDouble(rating);
            return this;
        }

        public FilterReportRow build() {
            return FilterReportRow.this;
        }
    }
}
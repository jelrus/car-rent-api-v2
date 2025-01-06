package com.car_rent_api.persistence.models.entity.types;

public enum BookingStatus {

    RESERVED("Reserved"),
    RESERVED_BY_SUPPORT_AGENT("Reserved by Support Agent"),
    SERVICE_STARTED("Service Started"),
    SERVICE_PROVIDED("Service Provided"),
    BOOKING_FINISHED("Booking Finished"),
    CANCELLED("Cancelled");

    private final String name;

    BookingStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
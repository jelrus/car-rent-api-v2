package com.car_rent_api.persistence.models.entity.types;

public enum BookingStatus {

    RESERVED("RESERVED"),
    RESERVED_BY_SUPPORT_AGENT("RESERVED_BY_SUPPORT_AGENT"),
    SERVICE_STARTED("SERVICE_STARTED"),
    SERVICE_PROVIDED("SERVICE_PROVIDED"),
    BOOKING_FINISHED("BOOKING_FINISHED"),
    CANCELLED("CANCELLED");

    private final String name;

    BookingStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
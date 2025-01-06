package com.backend.models.table.types;


public enum BookingStatus {

    RESERVED("RESERVED"),
    RESERVED_BY_SUPPORT_AGENT("RESERVED_BY_SUPPORT_AGENT"),
    SERVICE_STARTED("SERVICE_STARTED"),
    SERVICE_PROVIDED("SERVICE_PROVIDED"),
    BOOKING_FINISHED("BOOKING_FINISHED"),
    CANCELLED("CANCELLED");

    private final String status;

    BookingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
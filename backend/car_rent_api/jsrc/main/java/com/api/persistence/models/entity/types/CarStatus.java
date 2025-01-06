package com.api.persistence.models.entity.types;

public enum CarStatus {

    AVAILABLE("Available"),
    BOOKED("Booked"),
    UNAVAILABLE("Unavailable");

    private final String name;

    CarStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
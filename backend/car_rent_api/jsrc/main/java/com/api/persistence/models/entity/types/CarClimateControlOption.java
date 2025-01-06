package com.api.persistence.models.entity.types;

public enum CarClimateControlOption {

    NONE("None"),
    AIR_CONDITIONER("Air Conditioner"),
    CLIMATE_CONTROL("Climate Control"),
    TWO_ZONE_CLIMATE_CONTROL("Two Zone Climate Control");

    private final String name;

    CarClimateControlOption(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
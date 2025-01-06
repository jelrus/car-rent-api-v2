package com.car_rent_api.persistence.models.entity.types;

public enum CarGearBoxType {

    MANUAL("Manual"),
    AUTOMATIC("Automatic");

    private final String name;

    CarGearBoxType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
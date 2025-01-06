package com.car_rent_api.persistence.models.entity.types;

public enum CarFuelType {

    PETROL("Petrol"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid");

    private final String name;

    CarFuelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
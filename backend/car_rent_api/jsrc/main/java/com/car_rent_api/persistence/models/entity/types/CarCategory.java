package com.car_rent_api.persistence.models.entity.types;

public enum CarCategory {

    ECONOMY("Economy"),
    COMFORT("Comfort"),
    BUSINESS("Business"),
    PREMIUM("Premium"),
    CROSSOVER("Crossover"),
    MINIVAN("Minivan"),
    ELECTRIC("Electric");

    private final String name;

    CarCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
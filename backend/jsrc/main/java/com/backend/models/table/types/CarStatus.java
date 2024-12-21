package com.backend.models.table.types;

/**
 * CarCategory is the enumeration class, which contains available statuses for Car object
 */
public enum CarStatus {

    /**
     * Represents available status
     */
    AVAILABLE("AVAILABLE"),

    /**
     * Represents booked status
     */
    BOOKED("BOOKED"),

    /**
     * Represents unavailable status
     */
    UNAVAILABLE("UNAVAILABLE");


    /**
     * Represent string value of status
     */
    private final String status;

    /**
     * Constructs CarStatus enumeration with status param
     *
     * @param status {@code String} requested status
     */
    CarStatus(String status) {
        this.status = status;
    }

    /**
     * Getter for status string value
     *
     * @return {@code String} status value
     */
    public String getStatus() {
        return status;
    }
}


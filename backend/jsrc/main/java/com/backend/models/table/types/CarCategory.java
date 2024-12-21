package com.backend.models.table.types;

/**
 * CarCategory is the enumeration class, which contains available categories for Car object
 */
public enum CarCategory {
    /**
     * Represents economy category
     */
    ECONOMY("ECONOMY"),

    /**
     * Represents comfort category
     */
    COMFORT("COMFORT"),

    /**
     * Represents business category
     */
    BUSINESS("BUSINESS"),
    /**
     * Represents premium category
     */
    PREMIUM("PREMIUM"),
    /**
     * Represents crossover category
     */
    CROSSOVER("CROSSOVER"),
    /**
     * Represents minivan category
     */
    MINIVAN("MINIVAN"),
    /**
     * Represents electric category
     */
    ELECTRIC("ELECTRIC");

    /**
     * Represent string value of category
     */
    private final String category;

    /**
     * Constructs CarCategory enumeration with category param
     *
     * @param category {@code String} requested category
     */
    CarCategory(String category) {
        this.category = category;
    }

    /**
     * Getter for category string value
     *
     * @return {@code String} category value
     */
    public String getCategory() {
        return category;
    }
}


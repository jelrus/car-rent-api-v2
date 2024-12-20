package com.backend.models.table.types;

/**
 * CarCategory is the enumeration class, which contains available categories for Car object
 */
public class CarCategory {

    public enum UserRole {

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
         * Represent string value of role
         */
        private final String role;

        /**
         * Constructs UserRole enumeration with role param
         *
         * @param role {@code String} requested role
         */
        UserRole(String role) {
            this.role = role;
        }

        /**
         * Getter for role string value
         *
         * @return {@code String} role value
         */
        public String getRole() {
            return role;
        }
    }
}

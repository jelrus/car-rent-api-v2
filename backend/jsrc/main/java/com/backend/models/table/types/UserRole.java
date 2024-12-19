package com.backend.models.table.types;

/**
 * UserRole is the enumeration class, which contains available roles for User object
 */
public enum UserRole {

    /**
     * Represents administrator role
     */
    ADMIN("Admin"),

    /**
     * Represents support agent role
     */
    SUPPORT_AGENT("Support Agent"),

    /**
     * Represents client role
     */
    CLIENT("Client");

    /**
     * Represent string value of role
     */
    private final String role;

    /**
     * Constructs UserRole enumeration with role param
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
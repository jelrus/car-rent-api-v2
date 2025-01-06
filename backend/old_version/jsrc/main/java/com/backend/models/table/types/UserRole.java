package com.backend.models.table.types;

/**
 * UserRole is the enumeration class, enumerates accessible roles for User entity.
 */
public enum UserRole {

    /**
     * Represents Admin role.
     */
    ADMIN("Admin"),

    /**
     * Represents Support Agent role.
     */
    SUPPORT_AGENT("Support Agent"),

    /**
     * Represents Client role.
     */
    CLIENT("Client");

    /**
     * Represents role in string format.
     */
    private final String role;

    /**
     * Constructs UserRole from specified string role value
     *
     * @param role {@code String} role string representation
     */
    UserRole(String role) {
        this.role = role;
    }

    /**
     * Getter for role field
     *
     * @return {@code String} accessed role field
     */
    public String getRole() {
        return role;
    }
}
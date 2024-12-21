package com.backend.models.table.types;


public enum UserRole {

    SUPPORT_AGENT("Support Agent"),
    CLIENT("Client");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
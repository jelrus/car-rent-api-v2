package com.api.persistence.models.entity.types;

public enum UserRole {

    CLIENT("Client"),
    SUPPORT_AGENT("Support Agent"),
    ADMIN("Admin");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
package com.uni.platform.vo;

import java.util.Optional;

public enum UserRole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public static Optional<UserRole> getValueByDescription(String description) {
        for (UserRole element: values()) {
            if (element.getDescription().equals(description)) {
                return Optional.of(element);
            }
        }
        return Optional.empty();
    }

    public String getDescription() {
        return description;
    }
}

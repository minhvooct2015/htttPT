package com.example;

public enum VaiTro {
    ADMIN("Admin"),
    USER("User"),
    MODERATOR("Moderator"),
    GUEST("Guest");

    private final String displayName;

    VaiTro(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static VaiTro fromString(String role) {
        for (VaiTro v : VaiTro.values()) {
            if (v.name().equalsIgnoreCase(role)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No enum constant " + role);
    }
}


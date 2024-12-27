package com.example;

public enum TrangThai {
    ACTIVE("Active"),
    BLOCKED("Blocked");

    private final String displayName;

    TrangThai(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


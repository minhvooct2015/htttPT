package com.example;

public enum VaiTro {
    ADMIN("Admin"),
    KHACH_HANG("Khách hàng");

    private final String displayName;

    VaiTro(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}



package com.example.enumss;

public enum PhuongThucGiaoHang {
    NHANH("Nhanh"),
    THUONG("Thường");

    private final String displayName;

    PhuongThucGiaoHang(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


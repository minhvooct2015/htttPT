package com.example.enumss;

public enum TrangThaiDonHang {
    DANG_XU_LY("Đang xử lý"),
    DA_GIAO("Đã giao"),
    HUY("Hủy");

    private final String displayName;

    TrangThaiDonHang(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


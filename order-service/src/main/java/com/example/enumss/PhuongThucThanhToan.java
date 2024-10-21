package com.example.enumss;

public enum PhuongThucThanhToan {
    THE("Thẻ"),
    TIEN_MAT("Tiền mặt");

    private final String displayName;

    PhuongThucThanhToan(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


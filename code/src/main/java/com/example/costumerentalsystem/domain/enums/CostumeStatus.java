package com.example.costumerentalsystem.domain.enums;

public enum CostumeStatus {
    AVAILABLE("ว่าง"),
    RESERVED("ติดจอง"),
    WAITING_RETURN("รอคืน"),
    LAUNDRY("ส่งซัก"),
    UNAVAILABLE("ไม่ว่าง");

    private final String displayName;

    CostumeStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
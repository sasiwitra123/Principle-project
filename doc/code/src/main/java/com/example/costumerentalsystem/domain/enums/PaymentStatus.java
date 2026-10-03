package com.example.costumerentalsystem.domain.enums;

public enum PaymentStatus {
    PENDING("รอตรวจสอบ"),
    VERIFIED("ตรวจสอบแล้ว"),
    REJECTED("ไม่ผ่าน");

    private final String displayName;

    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

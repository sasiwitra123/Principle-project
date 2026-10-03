package com.example.costumerentalsystem.domain.enums;

/**
 * วงจรการเช่า ฐานของ State Pattern 
 *
 * PENDING_PAYMENT -> PAID -> SHIPPED -> IN_USE -> RETURNED -> COMPLETED
 *        \-> CANCELLED (ยกเลิกได้ก่อนจัดส่ง)
 */
public enum RentalStatus {
    PENDING_PAYMENT("รอชำระเงิน"),
    PAID("ชำระเงินแล้ว"),
    SHIPPED("กำลังจัดส่ง"),
    IN_USE("กำลังใช้งาน"),
    RETURNED("คืนเรียบร้อย"),
    COMPLETED("เสร็จสิ้น"),
    CANCELLED("ยกเลิก");

    private final String displayName;

    RentalStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

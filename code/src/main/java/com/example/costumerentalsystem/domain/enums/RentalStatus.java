package com.example.costumerentalsystem.domain.enums;

import java.util.EnumSet;
import java.util.Set;

/**
 * สถานะของใบเช่า (State pattern ใช้ enum นี้เป็นฐาน)
 * PENDING_PAYMENT -> PAID -> SHIPPED -> IN_USE -> RETURNED -> COMPLETED
 * ยกเลิก (CANCELLED) ได้ตอนยังไม่จัดส่ง
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

    // สถานะที่ชุดยังถูกจองอยู่ ใช้เช็กวันเช่าซ้อน
    public static Set<RentalStatus> activeStatuses() {
        return EnumSet.of(PENDING_PAYMENT, PAID, SHIPPED, IN_USE, RETURNED);
    }
}

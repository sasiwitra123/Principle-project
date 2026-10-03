package com.example.costumerentalsystem.exception;

import com.example.costumerentalsystem.domain.enums.RentalStatus;

// เปลี่ยนสถานะผิดลำดับ (HTTP 409) ใช้ตัวนี้แทน UnsupportedOperationException ตามข้อ LSP
public class InvalidRentalTransitionException extends ConflictException {

    public InvalidRentalTransitionException(RentalStatus from, String action) {
        super("ไม่สามารถ " + action + " ได้ เมื่อสถานะการเช่าเป็น \"" + from.getDisplayName() + "\"");
    }
}

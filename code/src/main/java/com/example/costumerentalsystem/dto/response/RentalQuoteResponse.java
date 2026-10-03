package com.example.costumerentalsystem.dto.response;

import java.math.BigDecimal;

// ราคาที่คำนวณไว้ให้ดูก่อนกดเช่า (คิดผ่าน Strategy)
public record RentalQuoteResponse(
        int totalDays,
        BigDecimal rentalFee,
        BigDecimal depositAmount,
        BigDecimal totalAmount,
        String pricingStrategy) {
}

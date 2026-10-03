package com.example.costumerentalsystem.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CostumeRequest(
        @NotBlank(message = "กรุณาระบุชื่อชุด")
        @Size(max = 150, message = "ชื่อชุดยาวได้ไม่เกิน 150 ตัวอักษร")
        String name,

        @NotNull(message = "กรุณาเลือกหมวดหมู่")
        Long categoryId,

        @NotNull(message = "กรุณาระบุราคาเช่าต่อวัน")
        @Positive(message = "ราคาเช่าต้องมากกว่า 0")
        BigDecimal pricePerDay,

        String description,

        String imageUrl) {
}

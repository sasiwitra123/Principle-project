package com.example.costumerentalsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "กรุณาระบุชื่อหมวดหมู่")
        @Size(max = 100, message = "ชื่อหมวดหมู่ยาวได้ไม่เกิน 100 ตัวอักษร")
        String name,

        @Size(max = 500, message = "คำอธิบายยาวได้ไม่เกิน 500 ตัวอักษร")
        String description) {
}

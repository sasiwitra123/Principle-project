package com.example.costumerentalsystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "กรุณาระบุชื่อผู้ใช้")
        @Size(min = 4, max = 50, message = "ชื่อผู้ใช้ต้องยาว 4-50 ตัวอักษร")
        String username,

        @NotBlank(message = "กรุณาระบุรหัสผ่าน")
        @Size(min = 6, max = 72, message = "รหัสผ่านต้องยาว 6-72 ตัวอักษร")
        String password,

        @NotBlank(message = "กรุณาระบุอีเมล")
        @Email(message = "รูปแบบอีเมลไม่ถูกต้อง")
        String email,

        String fullName,

        String phone) {
}

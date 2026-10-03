package com.example.costumerentalsystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserProfileRequest(
        @Size(max = 120, message = "ชื่อยาวได้ไม่เกิน 120 ตัวอักษร")
        String fullName,

        @Pattern(regexp = "^$|^[0-9]{9,10}$", message = "เบอร์โทรต้องเป็นตัวเลข 9-10 หลัก")
        String phone,

        @NotBlank(message = "กรุณาระบุอีเมล")
        @Email(message = "รูปแบบอีเมลไม่ถูกต้อง")
        String email,

        String addressLine,
        String subDistrict,
        String district,
        String province,

        @Pattern(regexp = "^$|^[0-9]{5}$", message = "รหัสไปรษณีย์ต้องเป็นตัวเลข 5 หลัก")
        String postalCode) {
}

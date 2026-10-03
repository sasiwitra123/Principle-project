package com.example.costumerentalsystem.dto.response;

import com.example.costumerentalsystem.domain.enums.Role;

// ห้ามมี password
public record UserResponse(
        Long id,
        String username,
        Role role,
        String fullName,
        String phone,
        String email,
        String addressLine,
        String subDistrict,
        String district,
        String province,
        String postalCode) {
}

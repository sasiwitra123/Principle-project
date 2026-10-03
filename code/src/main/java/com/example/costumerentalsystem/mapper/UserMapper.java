package com.example.costumerentalsystem.mapper;

import org.springframework.stereotype.Component;

import com.example.costumerentalsystem.domain.entity.User;
import com.example.costumerentalsystem.domain.entity.UserProfile;
import com.example.costumerentalsystem.dto.request.UserProfileRequest;
import com.example.costumerentalsystem.dto.response.UserResponse;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        UserProfile p = user.getProfile();
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                p != null ? p.getFullName() : null,
                p != null ? p.getPhone() : null,
                user.getEmail(),
                p != null ? p.getAddressLine() : null,
                p != null ? p.getSubDistrict() : null,
                p != null ? p.getDistrict() : null,
                p != null ? p.getProvince() : null,
                p != null ? p.getPostalCode() : null);
    }

    // email อยู่ที่ตาราง users เลยไปตั้งที่ User ใน service
    public void apply(UserProfile profile, UserProfileRequest request) {
        profile.setFullName(request.fullName());
        profile.setPhone(request.phone());
        profile.setAddressLine(request.addressLine());
        profile.setSubDistrict(request.subDistrict());
        profile.setDistrict(request.district());
        profile.setProvince(request.province());
        profile.setPostalCode(request.postalCode());
    }
}

package com.example.costumerentalsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.costumerentalsystem.model.User;
import com.example.costumerentalsystem.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole("ROLE_USER");
        }
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 🟢 1. เพิ่มเมธอดค้นหาผู้ใช้ด้วย Email (รองรับกรณีใช้ Email เป็นหลัก)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // 🟢 2. เพิ่มเมธอดสำหรับอัปเดตข้อมูลส่วนตัวและที่อยู่
    public void updateUserProfile(String identifier, User updatedData) {
        // ค้นหาผู้ใช้จาก username ก่อน ถ้าไม่พบให้ค้นหาจาก email
        User user = userRepository.findByUsername(identifier);
        if (user == null) {
            user = userRepository.findByEmail(identifier).orElse(null);
        }

        if (user != null) {
            user.setFullName(updatedData.getFullName());
            user.setPhone(updatedData.getPhone());
            user.setAddressLine(updatedData.getAddressLine());
            user.setSubDistrict(updatedData.getSubDistrict());
            user.setDistrict(updatedData.getDistrict());
            user.setProvince(updatedData.getProvince());
            user.setPostalCode(updatedData.getPostalCode());

            userRepository.save(user);
        } else {
            throw new RuntimeException("ไม่พบข้อมูลผู้ใช้งานในระบบ");
        }
    }
}
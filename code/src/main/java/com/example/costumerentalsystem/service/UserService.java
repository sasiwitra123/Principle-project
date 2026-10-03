package com.example.costumerentalsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.costumerentalsystem.domain.entity.User;
import com.example.costumerentalsystem.domain.enums.Role;
import com.example.costumerentalsystem.repository.UserRepository;

@Service
public class UserService {

    // Constructor Injection 
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public void updateUserProfile(String identifier, User updatedData) {
        User user = userRepository.findByUsername(identifier);
        if (user == null) {
            user = userRepository.findByEmail(identifier).orElse(null);
        }
        if (user == null) {
            throw new RuntimeException("ไม่พบข้อมูลผู้ใช้งานในระบบ");
        }

        // getter/setter เหล่านี้ส่งต่อไปที่ UserProfile (1-1) — ดู User.java
        user.setFullName(updatedData.getFullName());
        user.setPhone(updatedData.getPhone());
        user.setAddressLine(updatedData.getAddressLine());
        user.setSubDistrict(updatedData.getSubDistrict());
        user.setDistrict(updatedData.getDistrict());
        user.setProvince(updatedData.getProvince());
        user.setPostalCode(updatedData.getPostalCode());

        userRepository.save(user);
    }
}

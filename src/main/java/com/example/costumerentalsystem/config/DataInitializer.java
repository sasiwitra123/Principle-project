package com.example.costumerentalsystem.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.costumerentalsystem.model.Costume;
import com.example.costumerentalsystem.model.User;
import com.example.costumerentalsystem.repository.CostumeRepository;
import com.example.costumerentalsystem.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CostumeRepository costumeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CostumeRepository costumeRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.costumeRepository = costumeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. บังคับสร้าง/อัปเดตบัญชี Admin
        String adminEmail = "sasiwitra.w@kkumail.com";
        String adminPassword = "12345inz";

        User admin = userRepository.findByUsername(adminEmail);
        if (admin == null) {
            admin = new User();
            admin.setUsername(adminEmail);
            admin.setEmail(adminEmail); // 🟢 เพิ่มการเซ็ต Email
            admin.setFullName("ผู้ดูแลระบบ Admin");
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole("ADMIN");
            userRepository.save(admin);
            System.out.println("✅ สร้างบัญชี Admin เรียบร้อย: " + adminEmail);
        } else {
            admin.setEmail(adminEmail); // 🟢 เพิ่มการเซ็ต Email
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole("ADMIN");
            userRepository.save(admin);
            System.out.println("✅ อัปเดตรหัสผ่าน Admin เรียบร้อย: " + adminEmail);
        }

        // 1.1 สร้างบัญชีผู้ใช้งานทั่วไปสำหรับทดสอบ
        if (userRepository.findByUsername("student@kkumail.com") == null) {
            User user1 = new User();
            user1.setUsername("student@kkumail.com");
            user1.setEmail("student@kkumail.com"); // 🟢 เพิ่มการเซ็ต Email
            user1.setFullName("นักศึกษา ทดสอบ");
            user1.setPassword(passwordEncoder.encode("123456"));
            user1.setRole("USER");
            userRepository.save(user1);
            System.out.println("✅ สร้างผู้ใช้งานตัวอย่างเรียบร้อย: student@kkumail.com (Password: 123456)");
        }

        // 2. เพิ่มข้อมูลชุดเช่าตัวอย่าง (ถ้ายังไม่มีข้อมูล)
        if (costumeRepository.count() == 0) {
            List<Costume> costumes = Arrays.asList(
                createCostume("ชุดสูทสากลสีกรมท่า", "ชุดสูท", 800.0, "ว่าง", "https://images.unsplash.com/photo-1594938298603-c8148c4dae35?w=500"),
                createCostume("ชุดไทยจักรพรรดิสีแดง", "ชุดไทย", 1500.0, "ว่าง", "https://images.unsplash.com/photo-1583391733958-3750e0ff4e8b?w=500"),
                createCostume("ชุดสูทสากลสีดำ", "ชุดสูท", 900.0, "ติดจอง", "https://images.unsplash.com/photo-1507679799987-c73779587ccf?w=500"),
                createCostume("ชุดราตรีกระโปรงยาวสีน้ำเงิน", "ชุดราตรี", 1200.0, "ว่าง", "https://images.unsplash.com/photo-1566174053879-31528523f8ae?w=500"),
                createCostume("ชุดกิโมโนประยุกต์", "ชุดแฟนซี", 1000.0, "ส่งซัก", "https://images.unsplash.com/photo-1578632767115-351597cf2477?w=500"),
                createCostume("ชุดไทยเรือนต้นสีทอง", "ชุดไทย", 1100.0, "รอคืน", "https://images.unsplash.com/photo-1610030469983-98e550d6193c?w=500")
            );
            costumeRepository.saveAll(costumes);
            System.out.println("✅ เพิ่มข้อมูลชุดตัวอย่างเรียบร้อย");
        }
    }

    private Costume createCostume(String name, String category, Double price, String status, String imageUrl) {
        return new Costume(name, category, price, null, status, imageUrl);
    }
}
package com.example.costumerentalsystem.domain.entity;

import com.example.costumerentalsystem.domain.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * บัญชีผู้ใช้ (ข้อมูลล็อกอิน) — ข้อมูลส่วนตัว/ที่อยู่แยกไปอยู่ที่ {@link UserProfile} (One-to-One)
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, length = 120)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role = Role.USER;

    /*
     * Cascade ALL + orphanRemoval: โปรไฟล์ไม่มีความหมายถ้าไม่มี User จึงบันทึก/ลบไปพร้อมกัน
     * Fetch LAZY: ไม่ดึงโปรไฟล์ทุกครั้งที่โหลด User
     * (หมายเหตุ: ฝั่ง inverse ของ 1-1 Hibernate อาจโหลดทันทีถ้าไม่มี bytecode enhancement)
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserProfile profile;

    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // ---------- helper ----------
    public void setProfile(UserProfile profile) {
        this.profile = profile;
        if (profile != null) {
            profile.setUser(this);
        }
    }

    private UserProfile ensureProfile() {
        if (profile == null) {
            setProfile(new UserProfile());
        }
        return profile;
    }

    // ---------- getters / setters ----------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    /** แปลงค่าว่างเป็น null — กัน UNIQUE(email) ชนกันเมื่อฟอร์มส่ง "" มาหลายคน */
    public void setEmail(String email) {
        this.email = (email == null || email.isBlank()) ? null : email.trim();
    }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public UserProfile getProfile() { return profile; }

    /*
     * ---- Transitional accessors (ชั่วคราว) ----
     * ฟอร์ม/เทมเพลตเดิม bind กับ user.fullName, user.phone ฯลฯ โดยตรง
     * เมธอดเหล่านี้ส่งต่อไปที่ UserProfile เพื่อให้หน้าเว็บเดิมยังทำงานได้
     * จะถูกลบเมื่อ Part C เปลี่ยนเป็น DTO (UserProfileRequest)
     */
    public String getFullName() { return profile == null ? null : profile.getFullName(); }
    public void setFullName(String v) { ensureProfile().setFullName(v); }

    public String getPhone() { return profile == null ? null : profile.getPhone(); }
    public void setPhone(String v) { ensureProfile().setPhone(v); }

    public String getAddressLine() { return profile == null ? null : profile.getAddressLine(); }
    public void setAddressLine(String v) { ensureProfile().setAddressLine(v); }

    public String getSubDistrict() { return profile == null ? null : profile.getSubDistrict(); }
    public void setSubDistrict(String v) { ensureProfile().setSubDistrict(v); }

    public String getDistrict() { return profile == null ? null : profile.getDistrict(); }
    public void setDistrict(String v) { ensureProfile().setDistrict(v); }

    public String getProvince() { return profile == null ? null : profile.getProvince(); }
    public void setProvince(String v) { ensureProfile().setProvince(v); }

    public String getPostalCode() { return profile == null ? null : profile.getPostalCode(); }
    public void setPostalCode(String v) { ensureProfile().setPostalCode(v); }
}

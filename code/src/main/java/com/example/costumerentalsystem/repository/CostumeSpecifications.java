package com.example.costumerentalsystem.repository;

import org.springframework.data.jpa.domain.Specification;

import com.example.costumerentalsystem.domain.entity.Costume;
import com.example.costumerentalsystem.domain.enums.CostumeStatus;

/**
 * คลาสสำหรับสร้างเงื่อนไขการค้นหาข้อมูลชุด (Costume) แบบไดนามิก (Dynamic Query)
 * โดยใช้ JPA Specification ในการประกอบเงื่อนไขต่างๆ เข้าด้วยกัน
 * 
 * เหตุผลที่ใช้ Specification:
 * การทำ Dynamic Query ที่ต้องเช็กค่า Null ใน JPQL มักพบปัญหากับ Dialect ของ PostgreSQL 
 * การใช้ Specification ช่วยลดข้อผิดพลาดในการต่อ Query และช่วยให้โค้ดอ่านง่าย ยืดหยุ่นขึ้น
 */
public final class CostumeSpecifications {

    // Private Constructor เพื่อป้องกันไม่ให้คลาสนี้ถูกสร้างเป็น Object (Utility Class)
    private CostumeSpecifications() {
    }

    /**
     * เงื่อนไขค้นหาชุดตามชื่อ (ค้นหาแบบ Partial Match / Case-Insensitive)
     * 
     * @param keyword คำค้นหาชื่อชุด
     * @return Specification หรือ null หากไม่ได้ส่งคำค้นหามา (ไม่กรองเงื่อนไขนี้)
     */
    public static Specification<Costume> nameContains(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return null;
        }
        // แปลงข้อความให้เป็นอักษรตัวพิมพ์เล็กทั้งหมดเพื่อรองรับ Case-Insensitive Search
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), pattern);
    }

    /**
     * เงื่อนไขค้นหาชุดตามหมวดหมู่ (Category ID)
     * 
     * @param categoryId รหัสหมวดหมู่
     * @return Specification หรือ null หากไม่ได้ระบุหมวดหมู่
     */
    public static Specification<Costume> inCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("category").get("id"), categoryId);
    }

    /**
     * เงื่อนไขค้นหาชุดตามสถานะ (CostumeStatus)
     * 
     * @param status สถานะของชุด (เช่น AVAILABLE, RENTED)
     * @return Specification หรือ null หากไม่ได้ระบุสถานะ
     */
    public static Specification<Costume> hasStatus(CostumeStatus status) {
        if (status == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }
}
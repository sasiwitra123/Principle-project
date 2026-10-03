package com.example.costumerentalsystem.domain.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * หมวดหมู่ชุด — One-to-Many กับ {@link Costume}
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 80)
    private String name;

    @Column(length = 255)
    private String description;

    /*
     * ไม่ใส่ cascade REMOVE โดยตั้งใจ: ลบหมวดหมู่แล้วชุดไม่ควรถูกลบตาม
     * (ฐานข้อมูลใช้ FK ป้องกันไว้อีกชั้น — ลบหมวดที่ยังมีชุดอยู่จะถูกปฏิเสธ)
     * Fetch LAZY: ไม่ต้องดึงรายการชุดทุกครั้งที่ดึงหมวดหมู่
     */
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Costume> costumes = new ArrayList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Costume> getCostumes() { return costumes; }

    /** ให้ toString() คืนชื่อหมวด */
    @Override
    public String toString() {
        return name;
    }
}

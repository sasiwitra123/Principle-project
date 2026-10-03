package com.example.costumerentalsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "costumes")
public class Costume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private Double price;

    // 🟢 1. เพิ่มฟิลด์ description (กำหนดเป็น TEXT เพื่อรองรับคำอธิบายยาวๆ)
    @Column(columnDefinition = "TEXT")
    private String description;

    private String status;
    private String imageUrl;

    public Costume() {
    }

    // 🟢 2. เพิ่ม description เข้าไปใน Constructor (เผื่อเรียกใช้งาน)
    public Costume(String name, String category, Double price, String description, String status, String imageUrl) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    // 🟢 3. เพิ่ม Getter และ Setter ของ description
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
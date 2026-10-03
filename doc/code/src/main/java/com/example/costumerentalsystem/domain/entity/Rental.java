package com.example.costumerentalsystem.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.costumerentalsystem.domain.enums.RentalStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

/**
 * การเช่า 1 รายการ
 * - User 1 คน มีได้หลาย Rental (One-to-Many จากฝั่ง User)
 * - Costume 1 ชุด มีได้หลาย Rental ในช่วงเวลาต่างกัน (One-to-Many จากฝั่ง Costume)
 * - Rental 1 รายการ มี Payment 1 รายการ และ Shipment 1 รายการ (One-to-One)
 *
 * สถานะใช้ {@link RentalStatus} ตัวเดียว (แทน status/rentalStatus/paymentStatus ที่ซ้ำกันเดิม)
 */
@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "costume_id", nullable = false)
    private Costume costume;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private int totalDays;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal depositAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private RentalStatus status = RentalStatus.PENDING_PAYMENT;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /*
     * Payment / Shipment เป็นของ Rental โดยตรง (ไม่มี Rental = ไม่มีทั้งสองอย่าง)
     * จึงใช้ cascade ALL + orphanRemoval, ส่วน FK อยู่ที่ตาราง payments / shipments (owning side)
     */
    @OneToOne(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Payment payment;

    @OneToOne(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Shipment shipment;

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    // ---------- helpers ----------
    public void setPayment(Payment payment) {
        this.payment = payment;
        if (payment != null) {
            payment.setRental(this);
        }
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
        if (shipment != null) {
            shipment.setRental(this);
        }
    }

    private Shipment ensureShipment() {
        if (shipment == null) {
            setShipment(new Shipment());
        }
        return shipment;
    }

    // ---------- getters / setters ----------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Costume getCostume() { return costume; }
    public void setCostume(Costume costume) { this.costume = costume; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public int getTotalDays() { return totalDays; }
    public void setTotalDays(int totalDays) { this.totalDays = totalDays; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public BigDecimal getDepositAmount() { return depositAmount; }
    public void setDepositAmount(BigDecimal depositAmount) { this.depositAmount = depositAmount; }

    public RentalStatus getStatus() { return status; }
    public void setStatus(RentalStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public Payment getPayment() { return payment; }
    public Shipment getShipment() { return shipment; }

    /*
     * ---- Transitional accessors (ชั่วคราว) ----
     * เทมเพลต/AdminController เดิมอ่านเขียน rental.courier / rental.trackingNo โดยตรง
     * ตอนนี้ข้อมูลอยู่ใน Shipment จึงส่งต่อให้ จะลบเมื่อ Part C เปลี่ยนเป็น DTO
     */
    public String getCourier() { return shipment == null ? null : shipment.getCourier(); }
    public void setCourier(String courier) { ensureShipment().setCourier(courier); }

    public String getTrackingNo() { return shipment == null ? null : shipment.getTrackingNo(); }
    public void setTrackingNo(String trackingNo) { ensureShipment().setTrackingNo(trackingNo); }
}

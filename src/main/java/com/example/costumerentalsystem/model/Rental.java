package com.example.costumerentalsystem.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "costume_id")
    private Costume costume;

    private LocalDate startDate;
    private LocalDate endDate;
    private int totalDays;
    private double totalPrice;
    
    private String paymentStatus; // PENDING, PAID, CANCELLED
    private String rentalStatus;  // WAITING_SHIPMENT, IN_USE, RETURNED, COMPLETED

    // Getters and Setters ...
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
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getRentalStatus() { return rentalStatus; }
    public void setRentalStatus(String rentalStatus) { this.rentalStatus = rentalStatus; }
    // เพิ่มตัวแปร 2 ตัวนี้ในคลาส Rental
private double depositAmount;
private String status;

// --- Getter & Setter สำหรับ depositAmount ---
public double getDepositAmount() {
    return depositAmount;
}

public void setDepositAmount(double depositAmount) {
    this.depositAmount = depositAmount;
}

// --- Getter & Setter สำหรับ status ---
public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
// เมธอดเชื่อมสำหรับ setTotalRentalFee
public void setTotalRentalFee(double totalRentalFee) {
    this.totalPrice = totalRentalFee;
}

public double getTotalRentalFee() {
    return this.totalPrice;
}
}
package com.example.costumerentalsystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    private Double amount;
    private String paymentType; // RENTAL_FEE, DEPOSIT, FINE
    private String paymentMethod; // BANK_TRANSFER, CASH, CREDIT_CARD
    private String slipImageUrl;
    private LocalDateTime paymentDate;
    private String status; // PENDING, VERIFIED, REJECTED

    public Payment() {
    }

    public Payment(Long id, Rental rental, Double amount, String paymentType, String paymentMethod, String slipImageUrl, LocalDateTime paymentDate, String status) {
        this.id = id;
        this.rental = rental;
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymentMethod = paymentMethod;
        this.slipImageUrl = slipImageUrl;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getSlipImageUrl() {
        return slipImageUrl;
    }

    public void setSlipImageUrl(String slipImageUrl) {
        this.slipImageUrl = slipImageUrl;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
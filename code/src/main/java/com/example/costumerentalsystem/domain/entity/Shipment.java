package com.example.costumerentalsystem.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/** ข้อมูลการจัดส่งของ Rental (ขนส่ง + เลขพัสดุ) — One-to-One (owning side: ถือ rental_id, UNIQUE) */
@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rental_id", nullable = false, unique = true)
    private Rental rental;

    @Column(length = 80)
    private String courier;

    @Column(length = 80)
    private String trackingNo;

    private LocalDateTime shippedAt;

    public Shipment() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Rental getRental() { return rental; }
    public void setRental(Rental rental) { this.rental = rental; }

    public String getCourier() { return courier; }
    public void setCourier(String courier) { this.courier = courier; }

    public String getTrackingNo() { return trackingNo; }
    public void setTrackingNo(String trackingNo) { this.trackingNo = trackingNo; }

    public LocalDateTime getShippedAt() { return shippedAt; }
    public void setShippedAt(LocalDateTime shippedAt) { this.shippedAt = shippedAt; }
}

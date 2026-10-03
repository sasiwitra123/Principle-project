package com.example.costumerentalsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.costumerentalsystem.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByStatus(String status);
}
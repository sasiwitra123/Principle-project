package com.example.costumerentalsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.costumerentalsystem.model.Costume;

@Repository
public interface CostumeRepository extends JpaRepository<Costume, Long> {
    List<Costume> findByStatus(String status);
    List<Costume> findByCategoryContainingIgnoreCaseOrNameContainingIgnoreCase(String category, String name);
    List<Costume> findByNameContainingIgnoreCase(String name);
}
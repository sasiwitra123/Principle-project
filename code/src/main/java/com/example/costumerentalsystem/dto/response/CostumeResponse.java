package com.example.costumerentalsystem.dto.response;

import java.math.BigDecimal;

import com.example.costumerentalsystem.domain.enums.CostumeStatus;

public record CostumeResponse(
        Long id,
        String name,
        Long categoryId,
        String categoryName,
        BigDecimal pricePerDay,
        String description,
        CostumeStatus status,
        String statusDisplayName,
        String imageUrl) {
}

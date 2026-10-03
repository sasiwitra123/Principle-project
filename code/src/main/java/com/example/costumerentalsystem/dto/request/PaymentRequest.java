package com.example.costumerentalsystem.dto.request;

import com.example.costumerentalsystem.domain.enums.PaymentMethod;

import jakarta.validation.constraints.NotNull;

public record PaymentRequest(
        @NotNull(message = "กรุณาเลือกวิธีชำระเงิน")
        PaymentMethod method,

        String slipImageUrl) {
}

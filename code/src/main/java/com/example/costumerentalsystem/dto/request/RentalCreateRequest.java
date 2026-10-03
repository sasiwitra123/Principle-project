package com.example.costumerentalsystem.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

// endDate ต้องไม่ก่อน startDate อันนี้เช็กใน service
public record RentalCreateRequest(
        @NotNull(message = "กรุณาเลือกชุด")
        Long costumeId,

        @NotNull(message = "กรุณาระบุวันเริ่มเช่า")
        @FutureOrPresent(message = "วันเริ่มเช่าต้องไม่เป็นวันในอดีต")
        LocalDate startDate,

        @NotNull(message = "กรุณาระบุวันคืน")
        LocalDate endDate) {
}

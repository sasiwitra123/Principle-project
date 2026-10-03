package com.example.costumerentalsystem.dto.response;

import java.time.Instant;
import java.util.List;

// รูปแบบ error ที่ REST API ส่งกลับ (พาร์ท B เอาไปใช้ใน GlobalExceptionHandler)
public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        List<FieldError> fieldErrors) {

    public record FieldError(String field, String message) {
    }

    public static ErrorResponse of(int status, String error, String message, String path) {
        return new ErrorResponse(Instant.now(), status, error, message, path, List.of());
    }
}

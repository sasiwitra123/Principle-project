package com.example.costumerentalsystem.exception;

// ขัดกับสถานะตอนนี้ เช่น ชื่อซ้ำ ชุดไม่ว่าง (HTTP 409)
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}

package com.example.costumerentalsystem.exception;

// ข้อมูลผิดกติกา เช่น วันคืนก่อนวันเริ่ม (HTTP 400)
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}

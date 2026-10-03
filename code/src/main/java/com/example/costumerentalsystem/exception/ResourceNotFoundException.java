package com.example.costumerentalsystem.exception;

// หาข้อมูลไม่เจอ (HTTP 404)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, Object id) {
        super("ไม่พบ" + resource + " (id: " + id + ")");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

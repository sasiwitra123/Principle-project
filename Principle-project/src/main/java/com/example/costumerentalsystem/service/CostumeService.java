package com.example.costumerentalsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.costumerentalsystem.model.Costume;
import com.example.costumerentalsystem.repository.CostumeRepository;

@Service
public class CostumeService {

    private final CostumeRepository costumeRepository;

    // ใช้ Constructor Injection แทน @Autowired บน Field
    public CostumeService(CostumeRepository costumeRepository) {
        this.costumeRepository = costumeRepository;
    }

    public List<Costume> getAllCostumes() {
        return costumeRepository.findAll();
    }

    public Costume getCostumeById(Long id) {
        return costumeRepository.findById(id).orElse(null);
    }

    public Costume saveCostume(Costume costume) {
        if (costume.getStatus() == null || costume.getStatus().trim().isEmpty()) {
            costume.setStatus("AVAILABLE");
        }
        return costumeRepository.save(costume);
    }

    // เพิ่มเมธอดนี้ในไฟล์ CostumeService.java
public List<Costume> searchCostumes(String keyword) {
    return costumeRepository.findByNameContainingIgnoreCase(keyword);
}
    // เพิ่ม: Method สำหรับเปลี่ยนสถานะชุด (AVAILABLE, RENTED, AWAITING_RETURN, MAINTENANCE)
    public Costume updateStatus(Long id, String status) {
        Costume costume = getCostumeById(id);
        if (costume != null) {
            costume.setStatus(status);
            return costumeRepository.save(costume);
        }
        return null;
    }

    public void deleteCostume(Long id) {
        costumeRepository.deleteById(id);
    }
}
package com.example.costumerentalsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.costumerentalsystem.domain.entity.Costume;
import com.example.costumerentalsystem.domain.enums.CostumeStatus;
import com.example.costumerentalsystem.repository.CostumeRepository;

@Service
public class CostumeService {

    private final CostumeRepository costumeRepository;

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
        if (costume.getStatus() == null) {
            costume.setStatus(CostumeStatus.AVAILABLE);
        }
        return costumeRepository.save(costume);
    }

    public List<Costume> searchCostumes(String keyword) {
        return costumeRepository.searchByNameOrCategory(keyword);
    }

    public Costume updateStatus(Long id, CostumeStatus status) {
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

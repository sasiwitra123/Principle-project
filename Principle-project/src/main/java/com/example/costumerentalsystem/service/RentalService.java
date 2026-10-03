package com.example.costumerentalsystem.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.costumerentalsystem.model.Costume;
import com.example.costumerentalsystem.model.Rental;
import com.example.costumerentalsystem.repository.CostumeRepository;
import com.example.costumerentalsystem.repository.RentalRepository;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final CostumeRepository costumeRepository;

    public RentalService(RentalRepository rentalRepository, CostumeRepository costumeRepository) {
        this.rentalRepository = rentalRepository;
        this.costumeRepository = costumeRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental createRental(Rental rental, Long costumeId) {
        Costume costume = costumeRepository.findById(costumeId).orElse(null);
        
        // เช็กสถานะชุดพร้อมเช่า ("ว่าง")
        if (costume == null || !"ว่าง".equalsIgnoreCase(costume.getStatus())) {
            throw new RuntimeException("ชุดไม่พร้อมสำหรับการเช่า");
        }

        long days = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
        if (days <= 0) days = 1;

        rental.setCostume(costume);
       // บรรทัดที่ 42
rental.setTotalPrice(days * costume.getPrice());

// บรรทัดที่ 45
rental.setDepositAmount(costume.getPrice() * 0.5);
        rental.setStatus("ACTIVE");

        // 3. ปรับสถานะชุดเป็น "ติดจอง"
        costume.setStatus("ติดจอง");
        costumeRepository.save(costume);

        return rentalRepository.save(rental);
    }

    public Rental returnCostume(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElse(null);
        if (rental != null) {
            rental.setStatus("RETURNED");
            
            // คืนสถานะชุดกลับเป็น "ว่าง"
            Costume costume = rental.getCostume();
            if (costume != null) {
                costume.setStatus("ว่าง");
                costumeRepository.save(costume);
            }
            return rentalRepository.save(rental);
        }
        return null;
    }
}
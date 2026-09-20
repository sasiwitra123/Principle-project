package com.example.costumerentalsystem.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.costumerentalsystem.model.Costume;
import com.example.costumerentalsystem.repository.CostumeRepository;
import com.example.costumerentalsystem.service.CostumeService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CostumeService costumeService;
    private final CostumeRepository costumeRepository;

    public AdminController(CostumeService costumeService, CostumeRepository costumeRepository) {
        this.costumeService = costumeService;
        this.costumeRepository = costumeRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("costumes", costumeService.getAllCostumes());
        model.addAttribute("rentals", new java.util.ArrayList<>()); 
        return "admin/dashboard";
    }

    // 🟢 1. เปิดหน้าฟอร์มเพิ่มชุดใหม่ (templates/admin/costume-form.html)
    @GetMapping("/costumes/new")
    public String showAddCostumeForm(Model model) {
        model.addAttribute("costume", new Costume());
        return "admin/costume-form";
    }

    // 🟢 2. บันทึกข้อมูลชุด + รองรับการอัปโหลดรูปภาพ
    @PostMapping("/costumes/save")
    public String saveCostume(@ModelAttribute("costume") Costume costume,
                              @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        
        // จัดการอัปโหลดรูปภาพ (ถ้าผู้ใช้เลือกไฟล์)
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path uploadDir = Paths.get("src/main/resources/static/uploads");
                
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }
                
                Files.copy(imageFile.getInputStream(), uploadDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                costume.setImageUrl("/uploads/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ตั้งค่าสถานะเริ่มต้นหากไม่ได้ระบุ
        if (costume.getStatus() == null || costume.getStatus().trim().isEmpty()) {
            costume.setStatus("ว่าง");
        }

        costumeRepository.save(costume);
        return "redirect:/admin/dashboard";
    }
}
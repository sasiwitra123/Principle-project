package com.example.costumerentalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.costumerentalsystem.model.Costume;
import com.example.costumerentalsystem.service.CostumeService;

@Controller
@RequestMapping("/costumes")
public class CostumeController {

    @Autowired
    private CostumeService costumeService;

    @GetMapping("/{id}")
    public String viewDetail(@PathVariable Long id, Model model) {
        Costume costume = costumeService.getCostumeById(id);
        model.addAttribute("costume", costume);
        return "costume-detail";
    }
}
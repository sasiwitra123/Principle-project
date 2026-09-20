package com.example.costumerentalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.costumerentalsystem.model.Rental;
import com.example.costumerentalsystem.service.CostumeService;
import com.example.costumerentalsystem.service.RentalService;

@Controller
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private CostumeService costumeService;

    @GetMapping("/new/{costumeId}")
    public String showRentalForm(@PathVariable Long costumeId, Model model) {
        Rental rental = new Rental();
        model.addAttribute("rental", rental);
        model.addAttribute("costume", costumeService.getCostumeById(costumeId));
        return "user/rental-form";
    }

    @PostMapping("/create")
    public String createRental(@ModelAttribute Rental rental, @RequestParam Long costumeId) {
        rentalService.createRental(rental, costumeId);
        return "redirect:/";
    }
}
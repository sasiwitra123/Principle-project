package com.example.costumerentalsystem.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.costumerentalsystem.model.User;
import com.example.costumerentalsystem.repository.UserRepository;
import com.example.costumerentalsystem.service.CostumeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final CostumeService costumeService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public HomeController(CostumeService costumeService, 
                          UserRepository userRepository, 
                          PasswordEncoder passwordEncoder) {
        this.costumeService = costumeService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. หน้าแรก: ค้นหา และแสดงชุดทั้งหมดพร้อมสถานะ (ว่าง, ติดจอง, รอคืน, ส่งซัก, ไม่ว่าง)
    @GetMapping("/")
    public String home(@RequestParam(value = "search", required = false) String search, 
                       Model model, 
                       HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser.getUsername());
            model.addAttribute("user", loggedInUser);
        } else {
            model.addAttribute("loggedInUser", "Guest");
        }

        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("costumes", costumeService.searchCostumes(search));
        } else {
            model.addAttribute("costumes", costumeService.getAllCostumes());
        }
        model.addAttribute("searchKeyword", search);

        return "index";
    }

    // 2. เข้าสู่ระบบ
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Model model) {
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("loggedInUser", user);
            
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                return "redirect:/admin/dashboard";
            }
            return "redirect:/";
        }

        model.addAttribute("error", "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง");
        return "login";
    }

    // 3. สมัครสมาชิก
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, HttpSession session, Model model) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "ชื่อผู้ใช้นี้มีอยู่ในระบบแล้ว");
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);

        // สมัครเสร็จทำการ Log-in ให้อัตโนมัติและไปที่หน้าแรก
        session.setAttribute("loggedInUser", user);
        return "redirect:/";
    }

    // 4. ออกจากระบบ
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login?logout";
    }
}
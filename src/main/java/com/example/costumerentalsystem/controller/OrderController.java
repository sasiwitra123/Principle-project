package com.example.costumerentalsystem.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/orders")
    public String myOrders(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("loggedInUser", principal.getName());
        }

        // ตัวอย่างข้อมูลออเดอร์จำลอง (ในอนาคตดึงจาก Database)
        List<Map<String, Object>> orders = new ArrayList<>();

        // ออเดอร์ที่ 1: กำลังจัดส่ง
        Map<String, Object> order1 = new HashMap<>();
        order1.put("orderId", "CR-20260901");
        order1.put("costumeName", "ชุดไทยศิวาลัย สีชมพูกลีบบัว");
        order1.put("costumeImage", "https://images.unsplash.com/photo-1583391733956-3750e0ff4e8b?w=300");
        order1.put("rentalDate", "20 ก.ย. 2026 - 23 ก.ย. 2026");
        order1.put("totalPrice", 2500);
        order1.put("depositPrice", 1000);
        order1.put("statusStep", 2); // 1: เตรียมส่ง, 2: กำลังส่ง, 3: เช่าอยู่, 4: ส่งคืน, 5: คืนเงินมัดจำแล้ว
        order1.put("trackingNo", "TH123456789EX");
        order1.put("courier", "Flash Express");
        order1.put("refundStatus", "รอตรวจสอบเมื่อส่งชุดคืน");
        orders.add(order1);

        // ออเดอร์ที่ 2: คืนเงินมัดจำเรียบร้อยแล้ว
        Map<String, Object> order2 = new HashMap<>();
        order2.put("orderId", "CR-20260815");
        order2.put("costumeName", "ชุดกิโมโนญี่ปุ่น ลายซากุระ");
        order2.put("costumeImage", "https://images.unsplash.com/photo-1578632767115-351597cf2477?w=300");
        order2.put("rentalDate", "15 ส.ค. 2026 - 18 ส.ค. 2026");
        order2.put("totalPrice", 1800);
        order2.put("depositPrice", 800);
        order2.put("statusStep", 5); // คืนเงินมัดจำสำเร็จ
        order2.put("trackingNo", "KER12398745");
        order2.put("courier", "Kerry Express");
        order2.put("refundStatus", "โอนเงินมัดจำคืนแล้ว 800 บาท (โอนเข้าบัญชีผู้ใช้เมื่อ 19 ส.ค. 2026)");
        orders.add(order2);

        model.addAttribute("orders", orders);
        return "orders";
    }
}
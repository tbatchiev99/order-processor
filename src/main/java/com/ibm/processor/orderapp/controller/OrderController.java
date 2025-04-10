package com.ibm.processor.orderapp.controller;

import com.ibm.processor.orderapp.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    final List<Order> orders = new ArrayList<>();

    @GetMapping("/orders")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("orders", orders);
        return "order-form";
    }

    @PostMapping("/orders")
    public String createOrder(@ModelAttribute Order order) {
        return "redirect:/orders";
    }

}

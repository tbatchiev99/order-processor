package com.ibm.processor.orderapp.controller;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private List<CreateOrderDto> orders = new ArrayList<>();

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new CreateOrderDto());
        model.addAttribute("orders", orders);
        return "order-form";
    }

    @PostMapping("/orders")
    public String saveOrder(@ModelAttribute CreateOrderDto order) {
        orderService.sendOrder(order);
        return "redirect:/orders";
    }

}

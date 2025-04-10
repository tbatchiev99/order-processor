package com.ibm.processor.orderapp.controller;

import com.ibm.processor.orderapp.dto.OrderDto;
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
    private List<OrderDto> orders = new ArrayList<>();

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new OrderDto());
        model.addAttribute("orders", orders);
        return "order-form";
    }

    @PostMapping("/orders")
    public String saveOrder(@ModelAttribute OrderDto order) {
        orderService.sendOrder(order);
        return "redirect:/orders";
    }

}

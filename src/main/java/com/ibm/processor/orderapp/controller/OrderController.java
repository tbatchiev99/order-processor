package com.ibm.processor.orderapp.controller;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.dto.ProductDto;
import com.ibm.processor.orderapp.service.OrderService;
import com.ibm.processor.orderapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;
    private List<CreateOrderDto> orders = new ArrayList<>();

    public OrderController(final ProductService productService, final OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String showOrderForm(Model model) {

        final List<ProductDto> productDtos = productService.getAllProducts();

        model.addAttribute("order", new CreateOrderDto());
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("products", productDtos);
        return "order-form";
    }

    @PostMapping("/orders")
    public String saveOrder(@ModelAttribute CreateOrderDto order) {
        orderService.sendOrder(order);
        return "redirect:/orders";
    }

}

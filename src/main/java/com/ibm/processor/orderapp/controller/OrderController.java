package com.ibm.processor.orderapp.controller;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.dto.OrderDto;
import com.ibm.processor.orderapp.dto.ProductDto;
import com.ibm.processor.orderapp.entity.Order;
import com.ibm.processor.orderapp.entity.Product;
import com.ibm.processor.orderapp.service.OrderService;
import com.ibm.processor.orderapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Controller
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;

    public OrderController(final ProductService productService, final OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String showOrderForm(Model model) {

        final List<ProductDto> productDtos = productService.getAllProducts().stream().map(this::toDto).toList();
        final List<OrderDto> orderDtos = orderService.getAllOrders().stream().map(this::toDto).toList();

        model.addAttribute("order", new CreateOrderDto());
        model.addAttribute("orders", orderDtos);
        model.addAttribute("products", productDtos);

        return "order-form";
    }

    @PostMapping("/orders")
    public String saveOrder(@ModelAttribute CreateOrderDto order, Model model) {

        boolean errorExists = false;
        String errorMessage = "";

        if (StringUtils.isEmpty(order.getName())) {
            errorExists = true;
            errorMessage = "Name must be provided!";
        }

        if (order.getName().length() > 100) {
            errorExists = true;
            errorMessage = "Name must not exceed 100 characters!";
        }

        if (order.getProductId() == null) {
            errorExists = true;
            errorMessage = "Product must be provided!";
        }

        if (order.getQuantity() < 0 || order.getQuantity() > 100) {
            errorExists = true;
            errorMessage = "You should enter a valid quantity!";
        }

        if (!errorExists) {
            orderService.sendOrder(order);
            return "redirect:/orders";
        } else {
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("errorExists", errorExists);
            final List<ProductDto> productDtos = productService.getAllProducts().stream().map(this::toDto).toList();
            model.addAttribute("products", productDtos);
            model.addAttribute("order", order);
            return "order-form";
        }

    }


    private ProductDto toDto(final Product product) {

        final ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());

        return productDto;
    }


    private OrderDto toDto(final Order order) {

        final OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setOrderNr(order.getOrderNr());
        orderDto.setOrderedOn(order.getOrderedOn());
        orderDto.setName(order.getName());
        orderDto.setProductName(order.getProduct().getName());
        orderDto.setStatus(order.getStatus().getName());

        return orderDto;
    }

}

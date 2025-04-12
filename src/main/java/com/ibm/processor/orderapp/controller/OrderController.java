package com.ibm.processor.orderapp.controller;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.dto.OrderDto;
import com.ibm.processor.orderapp.dto.ProductDto;
import com.ibm.processor.orderapp.entity.Product;
import com.ibm.processor.orderapp.service.OrderService;
import com.ibm.processor.orderapp.service.ProductService;
import com.ibm.processor.orderapp.util.OrderConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

import static com.ibm.processor.orderapp.util.Constants.*;

/**
 * Controller class that serves to populate the static web page with functionality.
 */
@Controller
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;

    public OrderController(final ProductService productService, final OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    /**
     * Populates the product dropdown, the order form and the order list.
     * @param model
     * @return redirect page
     */
    @GetMapping("/order-form")
    public String showOrderForm(Model model) {

        final List<ProductDto> productDtos = productService.getAllProducts().stream().map(this::toDto).toList();
        final List<OrderDto> orderDtos = orderService.getAllOrders()
                .stream()
                .map(OrderConverter::toDto)
        .toList();

        model.addAttribute("order", new CreateOrderDto());
        model.addAttribute("orders", orderDtos);
        model.addAttribute("products", productDtos);

        return "order-form";
    }

    /**
     * Validates and executes saving logic whenever the form is posted.
     * @param order
     * @param model
     * @return redirect page
     */
    @PostMapping("/order-submit")
    public String saveOrder(@ModelAttribute CreateOrderDto order, Model model) {

        boolean errorExists = false;
        String errorMessage = "";

        if (StringUtils.isEmpty(order.getName())) {
            errorExists = true;
            errorMessage = ORDER_NAME_NULL_ERROR_MESSAGE;
        }

        if (order.getName().length() > 100) {
            errorExists = true;
            errorMessage = ORDER_NAME_LENGTH_ERROR_MESSAGE;
        }

        if (order.getProductId() == null) {
            errorExists = true;
            errorMessage = ORDER_PRODUCT_NULL_ERROR_MESSAGE;
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
}

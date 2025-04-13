package com.ibm.processor.orderapp.controller.rest;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.dto.OrderDto;
import com.ibm.processor.orderapp.exception.BadRequestException;
import com.ibm.processor.orderapp.service.OrderService;
import com.ibm.processor.orderapp.util.OrderConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

import static com.ibm.processor.orderapp.util.Constants.*;

/**
 * Order controller class which introduces REST API endpoints for saving and fetching orders.
 */
@RestController
@RequestMapping("/api/v1")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(final OrderService orderService) {
        this.orderService = orderService;
    }


    @Operation(
            summary = "Get all orders",
            description = "Returns a list of all existing orders",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of orders",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class))))
            }
    )
    @GetMapping("/orders")
    public List<OrderDto> getAll() {
        return orderService.getAllOrders()
                .stream()
                .map(OrderConverter::toDto)
                .toList();
    }

    @Operation(
            summary = "Create a new order by sending it to the order processor thread by using the message broker. " +
                    "Saves to the database with the according order status.",
            description = "Creates a new order with the provided details by using the message provider",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order successfully created",
                            content = @Content(schema = @Schema(implementation = OrderDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid order data",
                            content = @Content(schema = @Schema(example = "{ \"message\": \"Validation failed\" }")))
            })
    @PostMapping("/order")
    public void save(@RequestBody CreateOrderDto orderDto) {

        if (StringUtils.isEmpty(orderDto.getName())) {
            throw new BadRequestException(ORDER_NAME_NULL_ERROR_MESSAGE);
        }

        if (orderDto.getName().length() > 100) {
            throw new BadRequestException(ORDER_NAME_LENGTH_ERROR_MESSAGE);
        }

        if (orderDto.getProductId() == null) {
            throw new BadRequestException(ORDER_PRODUCT_NULL_ERROR_MESSAGE);
        }

        orderService.sendOrder(orderDto);
    }
}

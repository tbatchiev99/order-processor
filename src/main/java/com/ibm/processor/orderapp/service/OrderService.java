package com.ibm.processor.orderapp.service;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    void sendOrder(final CreateOrderDto order);

    Order saveOrder(final CreateOrderDto orderDto);
    List<Order> getAllOrders();
}

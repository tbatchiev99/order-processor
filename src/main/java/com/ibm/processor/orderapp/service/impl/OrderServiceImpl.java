package com.ibm.processor.orderapp.service.impl;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.dto.OrderDto;
import com.ibm.processor.orderapp.entity.Order;
import com.ibm.processor.orderapp.repository.OrderRepository;
import com.ibm.processor.orderapp.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String SALT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    @Value(value = "${kafka.order.topic.name}")
    private String topicName;

    private final OrderRepository orderRepository;
    private final KafkaProducer kafkaProducer;

    public OrderServiceImpl(final OrderRepository orderRepository, final KafkaProducer kafkaProducer) {
        this.orderRepository = orderRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public void sendOrder(final CreateOrderDto order) {
        kafkaProducer.sendMessage(topicName, getSaltString(), order);
    }

    @Override
    public OrderDto saveOrder(final CreateOrderDto createOrderDto) {
        return null;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(this::toDto).toList();
    }

    private String getSaltString() {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALT_CHARS.length());
            salt.append(SALT_CHARS.charAt(index));
        }
        return salt.toString();

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

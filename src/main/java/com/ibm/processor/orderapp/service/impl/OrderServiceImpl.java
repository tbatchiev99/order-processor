package com.ibm.processor.orderapp.service.impl;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.dto.OrderDto;
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

    private KafkaProducer kafkaProducer;

    public OrderServiceImpl(final KafkaProducer kafkaProducer) {
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
        return List.of();
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
}

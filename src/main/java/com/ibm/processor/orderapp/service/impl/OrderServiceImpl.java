package com.ibm.processor.orderapp.service.impl;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.entity.Order;
import com.ibm.processor.orderapp.repository.OrderRepository;
import com.ibm.processor.orderapp.service.OrderService;
import com.ibm.processor.orderapp.service.ProductService;
import com.ibm.processor.orderapp.service.StatusService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String SUCCESSFULLY_PROCESSED = "Successfully processed";

    @Value(value = "${kafka.order.topic.name}")
    private String topicName;

    private final OrderRepository orderRepository;
    private final KafkaProducer kafkaProducer;
    private final StatusService statusService;
    private final ProductService productService;

    public OrderServiceImpl(final OrderRepository orderRepository, final KafkaProducer kafkaProducer, final StatusService statusService, final ProductService productService) {
        this.orderRepository = orderRepository;
        this.kafkaProducer = kafkaProducer;
        this.statusService = statusService;
        this.productService = productService;
    }

    public void sendOrder(final CreateOrderDto order) {
        kafkaProducer.sendMessage(topicName, order);
    }

    @Override
    public Order saveOrder(final CreateOrderDto createOrderDto) {

        Order order;

        try {
            order = toEntity(createOrderDto);
        } catch (final RuntimeException exception) {

        }

        return orderRepository.save(toEntity(createOrderDto));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private Order toEntity(final CreateOrderDto orderDto) {

        final Order order = new Order();

        order.setName(orderDto.getName());
        order.setOrderedOn(Instant.now());
        order.setQuantity(orderDto.getQuantity());
        order.setOrderNr(orderRepository.getNextOrderNr());
        order.setProduct(productService.findById(orderDto.getProductId()));
        order.setStatus(statusService.findByName(SUCCESSFULLY_PROCESSED));

        return order;
    }
}

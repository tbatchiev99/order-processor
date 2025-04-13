package com.ibm.processor.orderapp.service.impl;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.entity.Order;
import com.ibm.processor.orderapp.exception.NotFoundException;
import com.ibm.processor.orderapp.repository.OrderRepository;
import com.ibm.processor.orderapp.service.OrderService;
import com.ibm.processor.orderapp.service.ProductService;
import com.ibm.processor.orderapp.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private static final String SUCCESSFULLY_PROCESSED = "Successfully processed";
    private static final String NOT_PROCESSED = "Not processed";

    @Value(value = "${spring.kafka.order.topic.name}")
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
        log.info("Order creation Service sent the order request with name {} to the broker.", order.getName());
    }

    @Override
    public Order saveOrder(final CreateOrderDto createOrderDto) {
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

        validateOrder(order);

        return order;
    }

    /**
     * Validates if the order has a valid product and quantity. Sets the status to 'Not processed' if not a valid order.
     * @param order
     */
    public void validateOrder(final Order order) {

        boolean errorExists = false;

        if (order.getQuantity() > 100) {
            log.info("Order processing failed for Order nr {}! Quantity limit exceeded!", order.getOrderNr());
            errorExists = true;
        }

        order.setStatus(statusService.findByName(errorExists ? NOT_PROCESSED : SUCCESSFULLY_PROCESSED));

    }

}

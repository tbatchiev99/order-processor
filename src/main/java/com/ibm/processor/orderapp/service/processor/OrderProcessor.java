package com.ibm.processor.orderapp.service.processor;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessor {

    private final Logger log = LoggerFactory.getLogger(OrderProcessor.class);
    private final OrderService orderService;

    public OrderProcessor(final OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Todo: thread safety maybe
     * @param orderDto
     */
    @Async
    @KafkaListener(topics = "${kafka.order.topic.name}", groupId = "order-group-id")
    public void flightEventConsumer(final CreateOrderDto orderDto) {

        log.info("Processor {} received order -> {}", Thread.currentThread().getName(), orderDto);

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        orderService.saveOrder(orderDto);

    }
}

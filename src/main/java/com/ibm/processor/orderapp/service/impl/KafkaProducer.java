package com.ibm.processor.orderapp.service.impl;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.service.MessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer implements MessageProducer {

    private final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, CreateOrderDto> kafkaTemplate;

    public KafkaProducer(final KafkaTemplate<String, CreateOrderDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends the order request by using the Kafka message broker.
     * @param topic
     * @param order
     */
    public void sendMessage(String topic, CreateOrderDto order) {

        kafkaTemplate.send(topic, order);

        log.info("Kafka producer sent the order request {} to the broker for topic {}.", order.getName(), topic);
    }

}

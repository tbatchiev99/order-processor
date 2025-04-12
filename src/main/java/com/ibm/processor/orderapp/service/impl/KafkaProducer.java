package com.ibm.processor.orderapp.service.impl;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import com.ibm.processor.orderapp.service.MessageProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer implements MessageProducer {

    private final KafkaTemplate<String, CreateOrderDto> kafkaTemplate;

    public KafkaProducer(final KafkaTemplate<String, CreateOrderDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /*
    TODO: Investigate key
     */

    public void sendMessage(String topic, CreateOrderDto order) {
        kafkaTemplate.send(topic, order);
    }


}

package com.ibm.processor.orderapp.service.impl;

import com.ibm.processor.orderapp.dto.OrderDto;
import com.ibm.processor.orderapp.service.MessageProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer implements MessageProducer {

    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, OrderDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /*
    TODO: Investigate key
     */

    public void sendMessage(String topic, String key, OrderDto order) {
        kafkaTemplate.send(topic, key, order);
    }


}

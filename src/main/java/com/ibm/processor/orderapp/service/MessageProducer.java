package com.ibm.processor.orderapp.service;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import org.springframework.stereotype.Service;

@Service
public interface MessageProducer {

    void sendMessage(String topic, String key, CreateOrderDto order);

}

package com.ibm.processor.orderapp.service;

import com.ibm.processor.orderapp.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public interface MessageProducer {

    void sendMessage(String topic, String key, OrderDto order);

}

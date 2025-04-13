package com.ibm.processor.orderapp.config;

import com.ibm.processor.orderapp.dto.CreateOrderDto;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
public class KafkaConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.order.topic.name}")
    private String topicName;

    @Value(value = "${spring.kafka.producer.retries}")
    private String retries;

    @Value(value = "${spring.kafka.producer.retry.backoff.ms}")
    private String retryBackoff;

    @Value(value = "${spring.kafka.producer.delivery.timeout.ms}")
    private String deliveryTimeout;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
            configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic orderTopic() {
        return new NewTopic(topicName, 1, (short) 1);
    }

    @Bean
    public ProducerFactory<String, CreateOrderDto> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        configProps.put(RETRIES_CONFIG, retries);
        configProps.put(RETRY_BACKOFF_MS_CONFIG, retryBackoff);
        configProps.put(DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeout);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, CreateOrderDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}

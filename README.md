# Simple Order System

A simple order processing system built with **Spring Boot 3.4**, **Java 21**, **Thymeleaf**, and **Apache Kafka**. The application allows users to submit orders via a web interface and simulates order processing asynchronously using Kafka and a custom processor (in package service.processor). 

---

## Tech Stack

- **Java 21**
- **Spring Boot 3.4**
- **Thymeleaf** 
- **H2 Database** 
- **Apache Kafka** 
- **Springdoc OpenAPI** 
- **Docker** 
  
---

## Docker Compose Setup – Kafka + Order Processing System

This setup runs a local multi-broker (3 brokers) Kafka cluster with a order-system service and also a Kafka UI for monitoring.

## Kafka configuration

- Topic name: order-topic (9 partitions, replication factor: 3)
- Producer message retries: 20 , delay between retries: 1s, timeout time: 30s
- Consumer message retries: 10 , delay between retries: 2s
- Message dto object - CreateOrderDto in dto package
    
## Getting Started

### Run Locally (without Docker)

Prerequisite env variables:

${SPRING_KAFKA_BOOTSTRAPSERVERS} = localhost:29092,localhost:29093,localhost:29094

```bash
# Kafka must be running
./gradlew bootRun
# or
mvn spring-boot:run
```

### Running the app with Docker

```bash

./gradlew clean build

docker build -t order-system .

docker-compose up

```

## Accessing the system

Orders frontend UI: http://localhost:8080/orders

Swagger: http://localhost:8080/swagger-ui.html

Kafka-UI: http://localhost:8090

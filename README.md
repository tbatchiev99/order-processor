# Simple Order System

A simple order processing system built with **Spring Boot 3.4**, **Java 21**, **Thymeleaf**, and **Apache Kafka**. The application allows users to submit orders via a web interface and simulates order processing asynchronously using Kafka and a custom processor.

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

## Getting Started

### Run Locally (without Docker)

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

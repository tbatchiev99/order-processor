# Simple Order System

A simple order processing system built with **Spring Boot 3.4**, **Java 21**, **Thymeleaf**, and **Apache Kafka**. The application allows users to submit orders via a web interface and simulates order processing asynchronously using Kafka and a custom processor.

---

## Tech Stack

- **Java 21**
- **Spring Boot 3.4**
- **Thymeleaf** (template engine for the frontend)
- **Apache Kafka** (message broker for asynchronous order handling)
- **Springdoc OpenAPI** for Swagger support
- **Docker** for containerization
  
---

## 🚀 Getting Started

### Prerequisites

- Java 21
- Docker & Docker Compose
- Kafka (optional if using Docker setup)
- Maven or Gradle

### Run Locally (without Docker)

```bash
# Kafka must be running
./gradlew bootRun
# or
mvn spring-boot:run

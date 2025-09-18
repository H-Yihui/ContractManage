# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Contract Management System backend developed using Java and the Spring Boot framework. It is built and managed with Apache Maven.

The application follows a standard Spring Boot project structure, with code organized into packages for configuration, controllers, models (POJOs), mappers, and services.

## Key Technologies

* **Backend Framework:** Spring Boot 3.5.5
* **Language:** Java 21
* **Build Tool:** Apache Maven
* **ORM Framework:** MyBatis Plus 3.5.14
* **Database:** MySQL (using mysql-connector-j 8.4.0)
* **Connection Pooling:** Druid 1.2.27
* **Utilities:** Lombok
* **Testing:** JUnit 5, Mockito, MockMvc
* **Data Generation:** JavaFaker 1.0.2 (for testing)

## Code Architecture

The project follows a standard Spring Boot layered architecture:

* `com.ktriasia.contractmanager.Application` - Main application entry point with @SpringBootApplication annotation
* `com.ktriasia.contractmanager.config` - Configuration classes
* `com.ktriasia.contractmanager.controller` - REST controllers for handling HTTP requests
  * `ClauseController` - Provides endpoints for managing clauses (get all clauses, get by title, get by category, get all categories)
  * `ContractController` - Provides endpoints for managing contracts (create, delete, get elements, get clause elements)
  * `ContractElementController` - Provides endpoints for managing contract elements (create, update, delete)
* `com.ktriasia.contractmanager.model.pojo` - Data models (POJOs) with Lombok @Data annotation
* `com.ktriasia.contractmanager.model.dto` - Data Transfer Objects (DTOs) for API responses
* `com.ktriasia.contractmanager.model.enums` - Enumeration types
* `com.ktriasia.contractmanager.model.mapper` - MyBatis mapper interfaces
* `com.ktriasia.contractmanager.service` - Service layer interfaces
* `com.ktriasia.contractmanager.service.serviceImpl` - Service layer implementations using MyBatis Plus ServiceImpl
* `com.ktriasia.contractmanager.controller` - REST controllers for handling HTTP requests
  * `ClauseController` - Provides endpoints for managing clauses (get all clauses, get by title, get by category, get all categories)
  * `ContractController` - Provides endpoints for managing contracts (create, delete, get elements, get clause elements)
  * `ContractElementController` - Provides endpoints for managing contract elements (create, update, delete)

## Data Models

Core data models include:
* `Contract` - Represents a contract with properties like contractId, contractName, createdAt, and updatedAt
* `Clause` - Represents a clause within a contract with properties like clauseId, clauseCategory, title, and content
* `ContractElement` - Represents elements within a contract with properties like elementId, contractId, elementType, content, and sourceClauseId
* `ClauseCategory` - Enum representing categories of clauses (PAYMENT, DELIVERY, WARRANTY, etc.)
* `ElementType` - Enum representing types of contract elements (CLAUSE, HEADER, PARAGRAPH)

These models are mapped to database tables using MyBatis Plus annotations.

## DTO Models

* `Result<T>` - Unified response wrapper with code, message, and data fields
* `ClauseDTO` - Data Transfer Object for Clause entities
* `ContractDTO` - Data Transfer Object for Contract entities
* `ContractElementDTO` - Data Transfer Object for ContractElement entities

## Development Commands

This project uses the Maven Wrapper (`mvnw`), so you don't need to have Maven installed system-wide.

### Building the Project
```bash
./mvnw compile
```

To create a distributable WAR package:
```bash
./mvnw package
```

### Running the Application
```bash
./mvnw spring-boot:run
```

### Running Tests
```bash
./mvnw test
```

## Development Conventions

* **Package Structure:** Code is organized by feature/layer in packages under `com.ktriasia.contractmanager`
* **Data Objects (Entities):** Plain Old Java Objects (POJOs) are used for data modeling, with Lombok's `@Data` annotation to reduce boilerplate code
* **Data Access Layer (Mappers):** Mapper interfaces for data access are located in the `com.ktriasia.contractmanager.model.mapper` package
* **Service Layer:** Service implementations extend MyBatis Plus `ServiceImpl` and implement custom service interfaces
* **REST Controllers:** All controllers use `@CrossOrigin` annotation to enable CORS support
* **API Response Format:** All API responses follow the standardized `Result<T>` format with code, message, and data fields
* **Configuration:** Application configuration is managed in `src/main/resources/application.yaml`
* **Database:** A MySQL database is expected with connection details configured in `application.yaml`
* **Testing:** Comprehensive unit tests using JUnit 5, Mockito, and MockMvc for all controller methods

## API Response Format

All API endpoints return responses in the following standardized format:

```json
{
  "code": 200,
  "message": "成功消息",
  "data": {}
}
```

Common response codes:
- `200`: Success
- `201`: Created successfully
- `404`: Not found
- `500`: Internal server error
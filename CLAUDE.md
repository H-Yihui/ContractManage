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
* **Connection Pooling:** Druid
* **Utilities:** Lombok

## Code Architecture

The project follows a standard Spring Boot layered architecture:

* `com.ktriasia.contractmanager.Application` - Main application entry point with @SpringBootApplication annotation
* `com.ktriasia.contractmanager.config` - Configuration classes
* `com.ktriasia.contractmanager.controller` - REST controllers for handling HTTP requests
  * `ClauseController` - Provides endpoints for managing clauses (get all clauses, get by title, get by category, get all categories)
  * `ContractController` - Provides endpoints for managing contracts (create, delete, get elements, get clause elements)
  * `ContractElementController` - Provides endpoints for managing contract elements (create, update, delete)
* `com.ktriasia.contractmanager.model.pojo` - Data models (POJOs) with Lombok @Data annotation
* `com.ktriasia.contractmanager.model.mapper` - MyBatis mapper interfaces
* `com.ktriasia.contractmanager.service` - Service layer for business logic
  * `ClauseService` - Business logic for clause management
  * `ContractService` - Business logic for contract management
  * `ContractElementService` - Business logic for contract element management

## Data Models

Core data models include:
* `Contract` - Represents a contract with properties like contractId, contractName, createdAt, and updatedAt
* `Clause` - Represents a clause within a contract
* `ContractElement` - Represents elements within a contract
* `ClauseCategory` - Enum representing categories of clauses
* `ElementType` - Enum representing types of contract elements (CLAUSE, HEADER, PARAGRAPH)

These models are mapped to database tables using MyBatis Plus annotations.

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
* **Configuration:** Application configuration is managed in `src/main/resources/application.yaml`
* **Database:** A MySQL database is expected with connection details configured in `application.yaml`
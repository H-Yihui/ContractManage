# Project Overview

This is a Contract Management System backend developed using Java and the Spring Boot framework. It is built and managed with Apache Maven.

The application follows a standard Spring Boot project structure, with code organized into packages for configuration, controllers, models (POJOs), mappers, and services.

## Key Technologies

*   **Backend Framework:** Spring Boot 3.5.5
*   **Language:** Java 21
*   **Build Tool:** Apache Maven
*   **ORM Framework:** MyBatis Plus 3.5.5
*   **Database:** MySQL (inferred from `mysql-connector-j` dependency)
*   **Connection Pooling:** Druid
*   **Utilities:** Lombok

## Data Models

The core data models identified are:
*   `Contract`: Represents a contract with properties like `contractId`, `contractName`, `createdAt`, and `updatedAt`.
*   `Clause`: Represents a clause within a contract.

These models are mapped to database tables using MyBatis Plus annotations (`@TableName`, `@TableId`, etc.).

# Building and Running

This project uses the Maven Wrapper (`mvnw`), so you don't need to have Maven installed system-wide.

## Building the Project

To compile the project and download dependencies:
```bash
./mvnw compile
```

To create a distributable WAR package:
```bash
./mvnw package
```

## Running the Application

To run the application locally using the Spring Boot plugin:
```bash
./mvnw spring-boot:run
```

## Running Tests

To execute the test suite:
```bash
./mvnw test
```

# Development Conventions

*   **Package Structure:** The code is organized by feature/layer in packages under `com.ktriasia.contractmanager`, such as `config`, `controller`, `model`, `mapper`, and `service`.
*   **Data Objects (Entities):** Plain Old Java Objects (POJOs) are used for data modeling, with Lombok's `@Data` annotation to reduce boilerplate code. They are annotated with MyBatis Plus annotations for ORM mapping.
*   **Data Access Layer (Mappers):** Mapper interfaces for data access are located in the `com.ktriasia.contractmanager.mapper` package. They should extend `com.baomidou.mybatisplus.core.mapper.BaseMapper<T>` to inherit common CRUD operations.
*   **Service Layer:** The service layer contains business logic. It is recommended to create service interfaces that extend `com.baomidou.mybatisplus.extension.service.IService<T>` and implementation classes that extend `com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<M, T>`.
*   **Configuration:** Application configuration is managed in `src/main/resources/application.properties`.
*   **Database:** A MySQL database is expected. The connection details are configured in `application.properties`.
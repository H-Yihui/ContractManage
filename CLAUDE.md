# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Contract Management System backend developed using Java and the Spring Boot framework. It is built and managed with Apache Maven.

The application follows a standard Spring Boot project structure, with code organized into packages for configuration, controllers, models (POJOs), mappers, and services.

For detailed information about the database schema and table structures, please refer to the DataModel.md file located in the project root. When working with entity classes or database-related code, always consult DataModel.md to ensure alignment with the actual database structure.

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
  * `ContractController` - Provides endpoints for managing contracts (create, delete, get elements, get clause elements, create from template)
  * `ContractElementController` - Provides endpoints for managing contract elements (create, update, delete)
  * `ContractTemplateController` - Provides endpoints for managing contract templates (create, delete, update, get, get all, save configs, get configs)
* `com.ktriasia.contractmanager.model.pojo` - Data models (POJOs) with Lombok @Data annotation
* `com.ktriasia.contractmanager.model.dto` - Data Transfer Objects (DTOs) for API responses
* `com.ktriasia.contractmanager.model.enums` - Enumeration types
* `com.ktriasia.contractmanager.model.mapper` - MyBatis mapper interfaces
* `com.ktriasia.contractmanager.model.result` - **Response result classes (Result, ResponseCode)**
* `com.ktriasia.contractmanager.service` - Service layer interfaces
* `com.ktriasia.contractmanager.service.serviceImpl` - Service layer implementations using MyBatis Plus ServiceImpl

## Data Models

Core data models include:
* `Contract` - Represents a contract with properties like contractId, contractName, createdAt, and updatedAt
* `Clause` - Represents a clause within a contract with properties like clauseId, clauseCategory, title, and content
* `ContractElement` - Represents elements within a contract with properties like elementId, contractId, elementType, content, orderIndex, and sourceClauseId
* `ClauseCategory` - Enum representing categories of clauses (PAYMENT, DELIVERY, WARRANTY, etc.)
* `ElementType` - Enum representing types of contract elements (CLAUSE, HEADER, PARAGRAPH) with enhanced fromString() method for decoupling
* `ContractTemplate` - Represents a contract template with properties like templateId, templateName, description, isActive, createdAt, and updatedAt
* `TemplateElementConfig` - Configuration for template elements with properties like configId, templateId, orderIndex, elementType, contentSource, etc.

## Recent Architecture Improvements

### Global Exception Handling (2025-09-23)
**Service Layer Exception Management:**
- **ServiceException Integration**: All service layer implementations now throw `ServiceException` with specific `ResponseCode` for different error scenarios
- **Structured Error Codes**: Extended `ResponseCode` enum with business-specific error codes:
  - Contract operations (1000-1999): `CONTRACT_NOT_FOUND`, `CONTRACT_CREATE_ERROR`, `CONTRACT_DELETE_ERROR`, etc.
  - Element operations (2000-2999): `ELEMENT_NOT_FOUND`, `ELEMENT_CREATE_ERROR`, `ELEMENT_UPDATE_ERROR`, etc.
  - Template operations (3000-3999): `TEMPLATE_NOT_FOUND`, `TEMPLATE_CREATE_ERROR`, `TEMPLATE_CONFIG_SAVE_ERROR`, etc.
  - Clause operations (4000-4999): `CLAUSE_NOT_FOUND`, `CLAUSE_CATEGORY_INVALID`, `CLAUSE_OPERATION_ERROR`, etc.
- **Error Response Format**: Global exception handler returns "[业务异常码]业务异常信息:具体的错误信息"
- **Service Layer Changes**: All service implementations now use try-catch blocks and throw appropriate `ServiceException` instances
- **Test Updates**: Unit tests updated to verify exception handling mechanisms

**Benefits:**
- Consistent error handling across all service layers
- Better error categorization and debugging capabilities
- Centralized exception management through global exception handler
- Improved API response consistency

### Package Refactoring (2025-09-23)
**Response Classes Relocation:**
- Moved `Result<T>` class from `com.ktriasia.contractmanager.model.dto` to `com.ktriasia.contractmanager.model.result`
- Moved `ResponseCode` enum from `com.ktriasia.contractmanager.model.constants` to `com.ktriasia.contractmanager.model.result`
- **Breaking Change:** All imports throughout the codebase have been updated to use the new package locations
- **Rationale:** Better organization by grouping all response-related classes in a dedicated package

### Decoupling Refactoring (2025-09-22)
The system has been significantly refactored to reduce coupling between ContractTemplate and Contract entities:

1. **Enhanced ElementType Enum**: Added `fromString()` static method to handle string-to-enum mapping with alias support and error handling
2. **Template Converter**: Created `TemplateToContractConverter` component to separate template conversion logic from service layer
3. **Dedicated DTO**: Introduced `CreateContractFromTemplateDTO` for better API parameter handling
4. **Service Layer Simplification**: Reduced `createContractFromTemplate()` method complexity by delegating conversion logic

### Key Components

#### TemplateToContractConverter
Located in `com.ktriasia.contractmanager.service.converter`, this component:
- Handles conversion from template configurations to contract elements
- Manages content sourcing (STATIC vs CLAUSE_LIBRARY)
- Maintains proper ordering and attributes mapping
- Encapsulates conversion logic for better maintainability

#### CreateContractFromTemplateDTO
New DTO in `com.ktriasia.contractmanager.model.dto` that includes:
- `templateId` - Template identifier
- `contractName` - Name for the new contract
- `description` - Optional contract description
- `customAttributes` - Optional JSON attributes for extensibility

#### Enhanced ElementType Methods
The ElementType enum now includes:
- `fromString(String raw)` - Converts string values to enum with alias support
- `isTextElement()`, `isStructuralElement()`, `isFormElement()`, `isSignatureElement()` - Type checking methods

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
* **Data Objects (Entities):** Plain Old Java Objects (POJOs) are used for data modeling, with Lombok's `@Data` annotation to reduce boilerplate code. For detailed information about entity fields and database mappings, refer to DataModel.md.
* **Data Access Layer (Mappers):** Mapper interfaces for data access are located in the `com.ktriasia.contractmanager.model.mapper` package
* **Service Layer:** Service implementations extend MyBatis Plus `ServiceImpl` and implement custom service interfaces
* **REST Controllers:** All controllers use `@CrossOrigin` annotation to enable CORS support
* **API Response Format:** All API responses follow the standardized `Result<T>` format with code, message, and data fields (located in `com.ktriasia.contractmanager.model.result`)
* **Configuration:** Application configuration is managed in `src/main/resources/application.yaml`
* **Database:** A MySQL database is expected with connection details configured in `application.yaml`. For detailed schema information, refer to DataModel.md.
* **Testing:** Comprehensive unit tests using JUnit 5, Mockito, and MockMvc for all controller methods

## API Response Format

All API endpoints return responses in the following standardized format using the `Result<T>` class from `com.ktriasia.contractmanager.model.result`:

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
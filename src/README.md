# Student Document Service

## Overview

**Student Document Service** is a Spring Boot-based REST API that allows school users (students) to manage documents. The service provides CRUD operations for both users and documents, with secure authentication and role-based authorization.

The project is designed with a clean architecture in mind, following best practices for service layers, repositories, and mapping DTOs. It uses an in-memory H2 database for simplicity, and Spring Security for authentication and role management.

---

## Features

* **User Management**

    * Create and retrieve user accounts
    * Role-based access control (`USER` and `ADMIN`)
    * Password encoding for security

* **Document Management**

    * Create, read, update, and list documents
    * Tracks creator and last editor of each document
    * Secure endpoints accessible only to authenticated users

* **Security**

    * In-memory authentication for development/testing
    * Admin-only operations for deleting users and documents
    * Spring Security integration with basic authentication

* **Testing**

    * Unit tests for service layer with Mockito
    * Repository tests using H2 in-memory database
    * Real mappers used in service tests for accurate DTO conversion

---

## Tech Stack

* Java 17+
* Spring Boot 3.x
* Spring Data JPA
* Spring Security
* H2 Database (in-memory)
* MapStruct for DTO mapping
* JUnit 5, Mockito, AssertJ for testing

---

## Project Structure

```
src
 └─ main
     └─ java/com/matthew/student_document_service
         ├─ controller
         ├─ dto
         │   ├─ request
         │   └─ response
         ├─ entity
         ├─ mapper
         ├─ repository
         ├─ security
         └─ service
             └─ impl
 └─ test
     └─ java/com/matthew/student_document_service
         ├─ repository
         └─ service
```

---

## Getting Started

### Prerequisites

* Java 17+
* Maven or Gradle
* IDE (IntelliJ IDEA, Eclipse, VS Code)

### Build and Run

1. Clone the repository:

```bash
git clone <repository-url>
cd student-document-service
```

2. Build the project:

```bash
./mvnw clean install
```

3. Run the application:

```bash
./mvnw spring-boot:run
```

The API will start at `http://localhost:8080`.

---

## API Endpoints

* **Public Endpoint**

    * `GET /api/public/info` – Returns basic service info (no authentication required)

* **User Endpoints** (Authenticated)

    * `POST /api/users` – Create a new user
    * `GET /api/users/{id}` – Get user by ID
    * `GET /api/users` – List all users
    * `DELETE /api/users/{id}` – Delete user (ADMIN only)

* **Document Endpoints** (Authenticated)

    * `POST /api/documents` – Create a new document
    * `GET /api/documents/{id}` – Get document by ID
    * `GET /api/documents` – List all documents
    * `PUT /api/documents/{id}` – Update document
    * `DELETE /api/documents/{id}` – Delete document (ADMIN only)

---

## Testing

### Unit Tests

* **Service Layer**: Tests business logic using Mockito and real mappers.
* **Repository Layer**: Tests database queries using H2 in-memory database.

Run all tests:

```bash
./mvnw test
```

---

## Security Notes

* Current implementation uses **in-memory authentication** for demonstration purposes.
* In production, consider:

    * JWT authentication
    * Persistent user store (database)
    * Role-based access control at method level


---

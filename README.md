# Rent Cars Customer Microservice Management

## Overview

This project is a **Customer Microservice** designed to manage customer information for a car rental system. It provides RESTful APIs to perform CRUD (Create, Read, Update, Delete) operations on customer data, such as creating a new customer, searching customer details, updating customer information, and deleting customer records.

The microservice is part of a larger **Rent Cars System**, which allows users to rent cars online. The customer microservice ensures that customer data is managed efficiently and securely, enabling seamless integration with other microservices (car inventory, booking, payment).

---

## Business Needs

### 1. **Customer Management**
- **Create Customer**: Register new customers with details such as first name, last name, and email.
- **Search Customer**: Fetch customer details by ID or email.
- **Update Customer**: Modify customer information (update email or name).
- **Delete Customer**: Remove customer records when no longer needed.

### 2. **Validation**
- Ensure that customer data is valid (email format, non-empty fields).
- Prevent duplicate customer registrations using unique email addresses.

### 3. **Error Handling**
- Handle exceptions gracefully (customer not found, duplicate email).
- Provide meaningful error messages to API consumers.

### 4. **Scalability**
- Designed as a microservice to ensure scalability and independent deployment.
- Can be integrated with other microservices in the Rent Cars System.

---

## Features

- **RESTful APIs**: Exposes endpoints for customer management.
- **Validation**: Ensures data integrity using annotations like `@NotNull`, `@NotBlank`, and `@Email`.
- **Exception Handling**: Custom exceptions (`CustomerNotFoundException`, `CustomerAlreadyExistsException`) for better error management.
- **Database Integration**: Uses a relational database to store customer data.
- **Unit Testing**: Comprehensive unit tests for controllers, services, and repositories.
- **Docker Support**: Containerized for easy deployment and scalability.

---

## Technology Stack

### Backend
- **Java 8**: Primary programming language.
- **Spring Boot**: Framework for building the microservice.
- **Spring Data JPA**: For database interactions.
- **PostgreSQL**: In-memory database for development and testing.

### APIs
- **RESTful APIs**: Exposed using `@RestController`.
- **HTTP Methods**: `GET`, `POST`, `PUT`, `DELETE`.
- **Response Formats**: JSON.

### Validation
- **Bean Validation**: Using annotations like `@NotNull`, `@NotBlank`, and `@Email`.

### Exception Handling
- **Global Exception Handler**: Centralized exception handling using `@RestControllerAdvice`.
- **Custom Exceptions**: `CustomerNotFoundException`, `CustomerAlreadyExistsException`.

### Testing
- **JUnit 5**: For unit testing.
- **Mockito**: For mocking dependencies in unit tests.

### Build Tool
- **Maven**: For dependency management and building the project.

### Containerization
- **Docker**: For containerizing the application.

---

## API Endpoints

| HTTP Method | Endpoint                                          | Description                  |
|-------------|---------------------------------------------------|------------------------------|
| `GET`       | `http://localhost:8080/customer`                  | Search all customers.        |
| `GET`       | `http://localhost:8080/customer/{customerId}`     | Search a customer by ID.     |
| `POST`      | `http://localhost:8080/customer`                  | Create a new customer.       |
| `PUT`       | `http://localhost:8080/customer`                  | Update an existing customer. |
| `DELETE`    | `http://localhost:8080/customer/{customerId}`     | Delete a customer by ID.     |

---

## Example Requests and Responses

### 1. Create a Customer
**Request:**
```http request
POST http://localhost:8080/customer
Content-Type: application/json

{
  "firstName": "Luke",
  "lastName": "Skywalker",
  "email": "luke.skywalker@rebel.com"
}
```

**Response:**
```http
HTTP/1.1 201 Created
Content-Type: application/json

"550e8400-e29b-41d4-a716-446655440000"
```

### 2. Search a Customer
**Request:**
```http request
GET http://localhost:8080/customer/550e8400-e29b-41d4-a716-446655440000
```

**Response:**
```http
HTTP/1.1 302 Found
Content-Type: application/json

{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "firstName": "Luke",
  "lastName": "Skywalker",
  "email": "luke.skywalker@rebel.com"
}
```

---

## Getting Started
**Requirements**
 - Java 8;
 - Maven 3.x;
 - Docker;
 - IDE (IntelliJ IDEA, Eclipse);

## Steps to Run the Project

### 1. Clone the repository:
```bash
git clone https://github.com/leandrowiemesfilho/sibs-exercise.git
```

### 2. Navigate to the project directory:
```bash
cd sibs-exercise
```

### 3. Build project:
```bash
mvn clean install
```

### 4. Docker compose
```bash
docker compose up -d
```

### 5. Ensure postgres and pdamin containers are running
```bash
docker ps
```

### 6. Configure database
Access pgAdmin (`http://localhost:5050/`). In Dashboard `Add New Server` with the following configuration:
 - **General tab**
   - Name: cars-rent
 - **Connection tab**
   - Host name/adress: postgres
   - Port: 5432
   - Maintenance database: postgres
   - Username: postgres
   - Password: password

At new server, right-click on `cars-rent > Databases > Create... > Database`, with the following information:
 - **General tab**
   - Database: customer
   - Owner: postgres

### 7. Run the application:
```bash
mvn spring-boot:run
```

---

## Testing

To run the unit tests:
```bash
mvn test
```

---

## Future improvements
 - Add authentication and authorization (Spring Security).
 - Integrate with a cloud database (PostgreSQL on AWS RDS).
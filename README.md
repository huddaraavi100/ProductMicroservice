# Springboot Product and User Service
Microservice for Product and User Api

This is a Spring Boot-based RESTful API that manages **Product** and **Users** using two different databases:
- **PostgreSQL** for `Product` entities
- **MongoDB** for `User` entities

- ## Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA (PostgreSQL)
- Spring Data MongoDB
- Maven
- JUnit & Mockito (for unit testing)
- PostgreSQL
- MongoDB


### Product APIs (`/api`)
| Method | Endpoint           | Description                |
|--------|--------------------|----------------------------|
| GET    | `/api/products/{id}`| Get product by ID          |
| GET    | `/api/products`    | Get all products           |
| POST   | `/api/products`    | Create a new product       |
| PUT    | `/api/products/{id}` | Update a product by ID     |
| DELETE | `/api/products/{id}` | Delete a product by ID     |




### User APIs (`/api`)
| Method | Endpoint             | Description             |
|--------|----------------------|-------------------------|
| GET    | `/api/users/{id}`    | Get user by ID          |
| GET    | `/api/users`         | Get all users           |
| POST   | `/api/users`         | Add a new user          |
| PUT    | `/api/users/{id}`    | Update user by ID       |
| DELETE | `/api/users/{id}`    | Delete user by ID       |

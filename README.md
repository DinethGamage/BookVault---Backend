# Library Management System Backend

## Overview
A Spring Boot backend for managing library operations, including user authentication, book management, and book issue/return. The system supports role-based access (admin and user) and uses JWT for secure authentication.

## Features
- User registration and login (JWT authentication)
- Admin and user roles
- CRUD operations for books (admin only for add/update/delete)
- Book issue and return management
- MySQL database integration
- Secure endpoints with Spring Security
- CORS enabled for frontend integration

## Technologies
- Java 17+
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Lombok

## Getting Started
### Prerequisites
- Java 17 or higher
- Maven
- MySQL server

### Setup
1. Clone the repository
2. Configure `src/main/resources/application.properties` with your MySQL credentials
3. Build and run:
   ```
   mvn clean install
   mvn spring-boot:run
   ```
   The backend will start at `http://localhost:8080/api`

## API Endpoints
### Authentication
- `POST /api/auth/register_normal_user` – Register a normal user
- `POST /api/auth/login` – Login and receive JWT token

### Admin
- `POST /api/admin/register_admin_user` – Register an admin user (admin only)

### Books
- `GET /api/books/get_all_books` – Get all books
- `GET /api/books/get_book_by_id/{id}` – Get book by ID
- `POST /api/books/add_book` – Add a new book (admin only)
- `PUT /api/books/update_book/{id}` – Update book (admin only)
- `DELETE /api/books/delete_book/{id}` – Delete book (admin only)

### Issue Records
- `POST /api/issue_records/issue_book/{bookId}` – Issue a book
- `POST /api/issue_records/return_book/{issueRecordId}` – Return a book

## Environment Variables
- `spring.datasource.url` – MySQL connection URL
- `spring.datasource.username` – MySQL username
- `spring.datasource.password` – MySQL password
- `jwt.secretKey` – JWT secret key
- `jwt.expiration` – JWT expiration time (ms)

## Usage Notes
- Use JWT token in the `Authorization` header for protected endpoints: `Authorization: Bearer <token>`
- Admin-only endpoints require `ROLE_ADMIN`
- CORS is enabled for frontend integration

## License
This project is for educational purposes.


# BookVault Backend API 🚀

A robust Spring Boot backend API for the BookVault Library Management System, providing secure authentication, book management, and borrowing operations.

## 🚀 Features

- **JWT Authentication** - Secure token-based authentication with auto-expiration
- **Role-Based Access Control** - Admin and user roles with different permissions
- **Book Management** - Complete CRUD operations for library books
- **Issue/Return System** - Track book borrowing and returns
- **MySQL Integration** - Persistent data storage with JPA
- **CORS Enabled** - Ready for frontend integration
- **Secure Endpoints** - Spring Security protection for all routes
- **RESTful API** - Clean, standard REST endpoints

## 🛠️ Tech Stack

- **Java 17+** - Modern Java features
- **Spring Boot 3** - Framework for building applications
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database operations
- **MySQL** - Relational database
- **JWT** - JSON Web Tokens for authentication
- **Lombok** - Reduce boilerplate code
- **Maven** - Dependency management

## 📦 Installation

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Git

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/DinethGamage/BookVault---Backend.git
   cd BookVault---Backend
   ```

2. **Configure MySQL Database**

   Create a MySQL database:
   ```sql
   CREATE DATABASE library_db;
   ```

3. **Configure Application Properties**

   Update `src/main/resources/application.properties`:
   ```properties
   # Database Configuration
   spring.datasource.url=jdbc:mysql://localhost:3306/library_db
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   
   # JPA Configuration
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   
   # JWT Configuration
   jwt.secretKey=your_secret_key_here_make_it_long_and_secure
   jwt.expiration=86400000
   
   # CORS Configuration
   cors.allowed-origins=http://localhost:3000
   ```

4. **Build and Run**
   ```bash
   # Clean and install dependencies
   mvn clean install
   
   # Run the application
   mvn spring-boot:run
   ```

5. **Verify Installation**

   The API will be available at: `http://localhost:8080/api`

   Test with a simple GET request:
   ```bash
   curl http://localhost:8080/api/books/get_all_books
   ```

## 📁 Project Structure

```
src/main/java/
├── config/              # Configuration classes
│   ├── CorsConfig.java  # CORS configuration
│   └── SecurityConfig.java # Security configuration
├── controller/          # REST controllers
│   ├── AuthController.java
│   ├── BookController.java
│   └── IssueRecordController.java
├── dto/                 # Data Transfer Objects
├── entity/              # JPA entities
│   ├── User.java
│   ├── Book.java
│   └── IssueRecord.java
├── repository/          # JPA repositories
├── service/             # Business logic
│   ├── AuthService.java
│   ├── BookService.java
│   └── IssueRecordService.java
└── util/                # Utility classes
    └── JwtUtil.java
```

## 🔐 API Endpoints

### Authentication
| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| `POST` | `/api/auth/register_normal_user` | Register a new user | Public |
| `POST` | `/api/auth/login` | User login | Public |

### Admin Operations
| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| `POST` | `/api/admin/register_admin_user` | Register admin user | Admin Only |

### Books Management
| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| `GET` | `/api/books/get_all_books` | Get all books | Public |
| `GET` | `/api/books/get_book_by_id/{id}` | Get book by ID | Public |
| `POST` | `/api/books/add_book` | Add new book | Admin Only |
| `PUT` | `/api/books/update_book/{id}` | Update book | Admin Only |
| `DELETE` | `/api/books/delete_book/{id}` | Delete book | Admin Only |

### Issue Records
| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| `POST` | `/api/issue_records/issue_book/{bookId}` | Issue a book | Authenticated |
| `POST` | `/api/issue_records/return_book/{issueRecordId}` | Return a book | Authenticated |

## 🔑 Authentication

### JWT Token Usage
Include the JWT token in the Authorization header:
```
Authorization: Bearer your_jwt_token_here
```

### Example Login Request
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "your_username",
    "password": "your_password"
  }'
```

### Example Protected Request
```bash
curl -X POST http://localhost:8080/api/books/add_book \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer your_jwt_token" \
  -d '{
    "title": "Book Title",
    "author": "Author Name",
    "isbn": "1234567890",
    "quantity": 5
  }'
```

## 🗄️ Database Schema

### Users Table
- `id` (Primary Key)
- `username`
- `email`
- `password` (encrypted)
- `role` (ADMIN/USER)
- `firstName`
- `lastName`

### Books Table
- `id` (Primary Key)
- `title`
- `author`
- `isbn`
- `quantity`
- `isAvailable`

### Issue Records Table
- `id` (Primary Key)
- `user_id` (Foreign Key)
- `book_id` (Foreign Key)
- `issueDate`
- `dueDate`
- `returnDate`
- `isReturned`

## 🌍 Environment Variables

For production deployment, use environment variables:

```bash
# Database
DB_URL=jdbc:mysql://your-db-host:3306/library_db
DB_USERNAME=your_username
DB_PASSWORD=your_password

# JWT
JWT_SECRET=your_very_long_and_secure_secret_key
JWT_EXPIRATION=86400000

# CORS
CORS_ORIGINS=https://your-frontend-domain.com
```

## 🚀 Deployment

### Docker Deployment
1. **Create Dockerfile**
   ```dockerfile
   FROM openjdk:17-jdk-slim
   COPY target/library-management-*.jar app.jar
   EXPOSE 8080
   ENTRYPOINT ["java", "-jar", "/app.jar"]
   ```

2. **Build and Run**
   ```bash
   docker build -t bookvault-backend .
   docker run -p 8080:8080 bookvault-backend
   ```

### Heroku Deployment
1. **Create Procfile**
   ```
   web: java -jar target/library-management-*.jar
   ```

2. **Deploy**
   ```bash
   git add .
   git commit -m "Deploy to Heroku"
   git push heroku main
   ```

## 🧪 Testing

### Run Tests
```bash
mvn test
```

### API Testing with Postman
Import the API collection for testing:
- Authentication endpoints
- CRUD operations
- Role-based access

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🔗 Related Repositories

- [Frontend Repository](https://github.com/DinethGamage/BookVault---Frontend) - React.js Frontend

## 📞 Support

If you have any questions or issues, please open an issue in this repository.

## 🙏 Acknowledgments

- Built with Spring Boot and modern Java practices
- Secure authentication with JWT
- RESTful API design principles
- Community best practices

---

**Built with ☕ using Spring Boot and Java**
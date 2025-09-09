Spring Boot CRUD Operations

A simple Spring Boot REST API project that demonstrates CRUD (Create, Read, Update, Delete) operations using PostgreSQL as the database.
This project is designed for learning and interview preparation, showing how to build REST APIs with Spring Boot + Spring Data JPA + PostgreSQL.

🌟 Features

✅ Full CRUD Operations on Book entities.

✅ RESTful API endpoints for integration with frontend or Postman.

✅ PostgreSQL database using Spring Data JPA 

✅ Global exception handling for cleaner error management.

✅ Modular, clean project structure with Spring Boot best practices.

✅ Configurable profiles: application.properties (main) and application-local.properties (local testing).

🛠️ Tech Stack

Java 17+

Spring Boot 3

Spring Web

Spring Data JPA

📂 Project Structure
spring-boot-crud-operation
│
├─ src/main/java/jsp/springboot
│   ├─ controller/BookController.java
│   ├─ dao/BookDao.java
│   ├─ dto/ServerResponse.java
│   ├─ entity/Book.java
│   ├─ exception/GlobalExceptionHandler.java
│   ├─ exception/NoRecordFoundException.java
│   ├─ repository/BookRepository.java
│   ├─ service/BookService.java
│   └─ SpringBootCrudOperationApplication.java
│
├─ src/main/resources
│   ├─ application.properties          # Main credentials (ignored in GitHub)
│   └─ application-local.properties    # Local testing credentials
│
└─ pom.xml

⚙️ Getting Started
Prerequisites

Java 17+

PostgreSQL

Maven

IDE: IntelliJ IDEA, Eclipse, or VS Code

⚙️ Setup & Run

Clone the repo

git clone https://github.com/adhyatma07/spring-boot-crud-operations.git
cd spring-boot-crud-operations


Configure PostgreSQL in application.properties ( I named it as application-local.properties please change it to application.properties in your project)

spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Build the project

mvn clean install


Run the app

mvn spring-boot:run


Access the app

REST APIs → http://localhost:8080/api/...

📬 API Endpoints
Method	Endpoint	Description
GET	api/books	Retrieve all books
GET	api/books/{id}	Retrieve book by ID
POST	api/books	Add a new book
PUT	api/books/{id}	Update existing book
DELETE	api/books/{id}	Delete book by ID
GET	api/books/sort/{field}	Sort books by field (e.g., title)

All endpoints return JSON responses.

📖 Example JSON

Create Request (POST /api/items)

{
  "name": "Sample Item",
  "description": "This is a test item"
}


Response

{
  "id": 1,
  "name": "Sample Item",
  "description": "This is a test item"
}

🧑‍💻 Author

👤 Adhyatma Hawale

GitHub: adhyatma07

LinkedIn: https://www.linkedin.com/in/adhyatma-hawale07/

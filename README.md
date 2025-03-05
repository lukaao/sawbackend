# SAW BACKEND

## Overview

This is a Java Spring Boot application that uses MongoDB as its database and runs in a Docker container. The project is designed to provide a scalable and efficient solution for handling data while leveraging the flexibility of NoSQL databases.

## Features

- Java 17
- Spring Boot
- Spring Data MongoDB
- Spring Security
- MongoDB as database
- Docker for containerization
- Docker Compose for managing dependencies
- RESTful API endpoints
- Swagger API documentation
- Unit tests with JUnit 5 and Mockito
- Role-based authentication with ADMIN and USER roles

## Prerequisites

Before running the application, ensure you have the following installed:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Git](https://git-scm.com/downloads)
- [Java 17](https://adoptium.net/)
- [Maven](https://maven.apache.org/download.cgi)

## Installation and Setup

1. **Clone the repository**

   ```sh
   git clone https://github.com/lukaao/sawbackend.git
   cd sawbackend
   ```

2. **Install dependencies**

   ```sh
   mvn install
   ```

   This will download all required dependencies for the project.

3. **Start the application using Docker**

   ```sh
   docker-compose up -d
   ```

   This will start both the application and the MongoDB instance.

4. **Check if the application is running**
   Open your browser and go to:

   ```sh
   http://localhost:8080
   ```

5. **Stop the application**

   ```sh
   docker-compose down
   ```


## API Documentation

The project includes Swagger for API documentation. Once the application is running, you can access the Swagger UI at:

```sh
http://localhost:8080/swagger-ui/index.html#/
```

This interface provides an interactive way to explore and test the available API endpoints.

## User Roles and Authentication

The application supports role-based authentication with the following users:

- **Admin User**: Can perform all requests.
  - **Username**: `admin`
  - **Password**: `admin`
  - **Role**: `ADMIN`
- **Regular User**: Can only perform GET requests.
  - **Username**: `user`
  - **Password**: `user`
  - **Role**: `USER`

## Configuration (application.properties)

The application is configured using the `application.properties` file:

```properties
spring.application.name=sawbackend

# CONFIG DATABASE
spring.data.mongodb.uri=mongodb://localhost:27017/sawbackend
spring.data.mongodb.username=admin
spring.data.mongodb.password=admin
```

## Testing

This project includes unit tests using JUnit 5 and Mockito to ensure code quality and reliability. To run the tests, execute the following command:

```sh
mvn test
```

## Challenges Faced

During the development of this project, several challenges were encountered and overcome:

- **MongoDB Connection Issues**: Ensuring that the Spring Boot application properly connects to the MongoDB instance running in Docker required careful configuration of environment variables and networking.
- **Containerization with Docker**: Setting up a smooth and efficient Docker Compose configuration to seamlessly run both the application and the database.
- **Dependency Management**: Managing dependencies efficiently with Maven to avoid conflicts and ensure smooth builds.
- **Testing Strategy**: Implementing unit tests with JUnit 5 and Mockito to validate business logic and ensure the reliability of the application.
- **Role-based Authentication**: Implementing user roles to restrict access to certain endpoints and ensure proper authorization.

## Contributing

Feel free to fork this repository and open pull requests with improvements or bug fixes.


## Contact

For any issues or suggestions, please open an issue on GitHub or reach out via email: [lukaaobarcelos@gmail.com](mailto\:lukaaobarcelos@gmail.com).


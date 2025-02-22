
# Secure Backend with Spring Boot

This project is a secure backend service built using Spring Boot, providing functionalities such as user and admin management, authentication, and authorization using JWT. It utilizes MongoDB for data storage and exposes RESTful APIs for integration.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
  - [Admin Management](#admin-management)
  - [User Management](#user-management)
  - [Authentication and Authorization](#authentication-and-authorization)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

## Features

- **JWT Authentication:** Secure authentication using JSON Web Tokens.
- **User and Admin Management:** CRUD operations for users and admins.
- **Role-Based Access Control:** Secure access to API endpoints based on roles.
- **RESTful API:** Provides RESTful endpoints for easy integration.

## Technologies Used

- **Java 17**
- **Spring Boot 2.7.5**
- **MongoDB**
- **JWT (JSON Web Tokens)**
- **Maven**

## Getting Started

### Prerequisites

- Java 17
- Maven
- MongoDB

### Installation

1. **Clone the repository:**
   ```sh
   git clone git@github.com:MHR877/spring-boot-jwt-api.git
   cd spring-boot-jwt-api
   ```

2. **Build the project:**
   ```sh
   mvn clean install
   ```

3. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```

### Configuration

Ensure you have a running MongoDB instance and configure the following properties in `application.properties`:

```properties
spring.data.mongodb.uri=
spring.data.mongodb.database=
server.port=
spring.datasource.platform=
spring.main.allow-circular-references=
server.servlet.session.cookie.secure=
server.servlet.session.cookie.http-only=
server.servlet.session.cookie.same-site=
app.jwtSecret=
app.jwtExpirationMs=
```

## API Endpoints

### Admin Management

- **Get All Admins**
  - **Endpoint:** `GET /api/admins`
  - **Description:** Retrieves a list of all admins.
  - **Response:** `200 OK` with JSON array of admins.

- **Get Admin by ID**
  - **Endpoint:** `GET /api/admins/{id}`
  - **Description:** Retrieves an admin by their ID.
  - **Response:** `200 OK` with JSON object of the admin.

- **Create Admin**
  - **Endpoint:** `POST /api/admins`
  - **Description:** Creates a new admin.
  - **Request Body:** JSON object of the admin.
  - **Response:** `201 Created` with the created admin.

- **Delete Admin**
  - **Endpoint:** `DELETE /api/admins/{id}`
  - **Description:** Deletes an admin by their ID.
  - **Response:** `204 No Content`

### User Management

- **Get All Users**
  - **Endpoint:** `GET /api/users`
  - **Description:** Retrieves a list of all users.
  - **Response:** `200 OK` with JSON array of users.

- **Get User by ID**
  - **Endpoint:** `GET /api/users/{id}`
  - **Description:** Retrieves a user by their ID.
  - **Response:** `200 OK` with JSON object of the user.

- **Create User**
  - **Endpoint:** `POST /api/users`
  - **Description:** Creates a new user.
  - **Request Body:** JSON object of the user.
  - **Response:** `201 Created` with the created user.

- **Delete User**
  - **Endpoint:** `DELETE /api/users/{id}`
  - **Description:** Deletes a user by their ID.
  - **Response:** `204 No Content`

- **Get Users with False State**
  - **Endpoint:** `GET /api/users/state-false`
  - **Description:** Retrieves users with a false status.
  - **Response:** `200 OK` with JSON array of users.

- **Get Users with True State**
  - **Endpoint:** `GET /api/users/state-true`
  - **Description:** Retrieves users with a true status.
  - **Response:** `200 OK` with JSON array of users.

### Authentication and Authorization

- **Login**
  - **Endpoint:** `POST /api/auth/login`
  - **Description:** Authenticates a user and returns a JWT.
  - **Request Body:** JSON object with `email` and `password`.
  - **Response:** `200 OK` with JSON object containing the JWT.

- **Verify Token**
  - **Endpoint:** `POST /api/auth/verify-token`
  - **Description:** Verifies the provided JWT.
  - **Request Body:** JSON object with `token`.
  - **Response:** `200 OK` if valid, `401 Unauthorized` if invalid.

- **Logout**
  - **Endpoint:** `POST /api/auth/logout`
  - **Description:** Logs out the user.
  - **Response:** `200 OK`

- **Check Login Status**
  - **Endpoint:** `GET /api/auth/is-login`
  - **Description:** Checks if a user is logged in.
  - **Response:** `200 OK` with JSON object indicating login status.

- **Register User**
  - **Endpoint:** `POST /api/auth/register-user`
  - **Description:** Registers a new user.
  - **Request Body:** JSON object of the user.
  - **Response:** `201 Created` with the created user.

## Testing

To run tests, use the following command:

```sh
mvn test
```

## Contributing

Contributions are welcome! Please fork this repository and submit a pull request for any features, bug fixes, or enhancements.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Acknowledgements

Special thanks to the open-source community and all contributors.


Feel free to adjust the content to better suit your project's specifics and any additional details you'd like to include.

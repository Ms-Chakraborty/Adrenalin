## The Core Tech Stack

### • Backend
The server is built using **Java 21** and **Spring Boot 3.4.4**, managed by **Apache Maven**.  
It exposes a **REST API** using Spring Web to communicate with the frontend.

### • Frontend
The user interface is a **Single Page Application (SPA)** built with **React**.

### • Database
Data persistence is handled by a **PostgreSQL** relational database running in a **Docker** container.  
The application connects using the PostgreSQL driver and **Spring Data JPA**, which uses **Hibernate** to automatically manage the database schema during development.

### • Security
**Keycloak** is used as the authorization server (running in Docker) to handle user management and authentication.  
The Spring Boot backend is configured as an **OAuth2 resource server** using Spring Security to validate **JWTs** issued by Keycloak.

### • Utilities
The project uses **Project Lombok** to reduce boilerplate code and **MapStruct** to automate object mapping between entities and DTOs.  
**Adminer** is included in the Docker Compose configuration to provide a web-based interface for database management.

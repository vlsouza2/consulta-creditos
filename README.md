# 🚀 Application 

**Java 11 | Spring Boot 2.7 | Angular 16 | PostgreSQL**


## 📌 Features

- **Backend**: 
  - REST API with Spring Boot 2.7
  - JPA/Hibernate + PostgreSQL
  - JWT Authentication
  - Swagger Documentation
- **Frontend**:
  - Angular 16 (Standalone Components)
  - Responsive UI with Angular Material
  - JWT Interceptor
- **DevOps**:
  - Dockerized containers
  - CI/CD ready
  - Multi-stage builds

## 🛠️ Prerequisites

- Java JDK 11
- Node.js 18.x
- Angular CLI 16.x
- PostgreSQL 17+
- Docker 20.10+
- Maven 3.8+

## 🏗️ Project Structure
app/
app-desafio/
├── backend/                  # Spring Boot Application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/desafio/
│   │   │   │   ├── config/      # Configuration classes
│   │   │   │   ├── controller/  # REST controllers
│   │   │   │   ├── model/       # JPA entities
│   │   │   │   ├── repository/  # Spring Data repositories
│   │   │   │   ├── service/     # Business logic
│   │   │   │   └── Application.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── application-dev.properties
│   │   └── test/              # Test classes
│   ├── Dockerfile            # Docker configuration for backend
│   └── pom.xml
│
├── frontend/                 # Angular Application
│   ├── src/
│   │   ├── app/
│   │   │   ├── core/         # Core modules
│   │   │   ├── modules/      # Feature modules
│   │   │   └── shared/       # Shared components
│   │   ├── assets/
│   │   └── environments/
│   ├── Dockerfile            # Docker configuration for frontend
│   ├── angular.json
│   └── package.json
│
├── docker-compose.yml        # Orchestration file
└── README.md



## 🚀 Quick Start

### Local Development

1. **Database Setup**:
   ```bash
   docker run --name postgres-db -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres:17

   Backend:

bash
cd backend
mvn spring-boot:run
API will run at http://localhost:8080

Frontend:

bash
cd frontend
npm install
ng serve
App will run at http://localhost:4200

Docker Deployment
bash
docker-compose -f docker/docker-compose.yml up --build

⚙️ Configuration
File	Purpose
backend/src/main/resources/application.properties	DB config, server port

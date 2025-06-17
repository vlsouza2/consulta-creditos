# 🚀 Application 

**Java 11 | Spring Boot 2.7 | Angular 16 | PostgreSQL**


## 📌 Features

- **Backend**: 
  - REST API with Spring Boot 2.7
  - JPA + PostgreSQL
- **Frontend**:
  - Angular 16 (Standalone Components)
  - Responsive UI with Angular
- **DevOps**:
  - Dockerized containers

## 🛠️ Prerequisites

- Java JDK 11
- Node.js 18.x
- Angular CLI 16.x
- PostgreSQL 17+
- Docker 20.10+
- Maven 3.8+


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

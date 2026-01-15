# Video Game Profile Management API - DevOps demonsrtation

**Made by** : Kossay Zemzem - Groupe C

This project is a simple backend API built with Spring Boot that allows for managing profiles of players of a video game. It acts as a backup API system to save user profiles across their devices ( Similar to how plateforms like Steam use save your progress online using Steam Cloud )

The goal of this project is demonstrate DevOps practices in a practical project.

## Official docker repository

You can check the [Docker hub repoistory](https://hub.docker.com/r/kossayzemzem/profile-api) or use pull the image using :

```bash
docker pull kossayzemzem/profile-api:latest
```

## Technologies & Tools

### Core Framework & Language

- **Java 17** - Programming language
- **Spring Boot 3.5.9** - Application framework
- **Maven** - Build automation and dependency management
- **H2**- Embeded database

### CI/CD

- **GitHub Actions** - Continuous Integration/Continuous Deployment

### Observability & Monitoring

- **Logback** - Spring Boot Logging framework
- **Micrometer Tracing** - Distributed tracing with OpenTelemetry bridge
- **Prometheus** - Metrics registry for monitoring (Dashboard is not implemented yet)

### Containerization & Deployment

- **Docker** - Container platform
- **Docker Compose** - Multi-container orchestration
- **Docker hub** - Container registry  
- **QEMU & Docker Buildx** - Multi-platform image builds , caching and automated pushing to container registery

### Security & Testing

- **SAST (Static Application Security Testing)**
  
  - **Gitleaks** - Scan for hardcoded secrets
  - **SonarCloud** - Code quality analysis (GitHub App)

- **DAST (Dynamic Application Security Testing)**
  
  - **OWASP ZAP (zaproxy/action-baseline)** - Passive ssecuirty scanning of the deployed API
  - **Spring Boot Test** - Unit testing using built in Maven test

---

## Local Setup

**Prerequisites** 

- Java 17 or higher

- Maven 

### How to run

#### 1. Clone the Repository

```bash
git clone https://github.com/Kossay-Zemzem/game-progress-cloud-saving-API. git

cd game-progress-cloud-saving-API
```

#### 2. Run the Application

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

#### 3. Verify the Application

Check the health endpoint:

```bash
curl http://localhost:8080/actuator/health
```

#### Configuration

The application uses the following default configuration 

- **Port**: `8080`

- **Database**: H2 file-based database stored in `./data/profileDB`

- **Logs**: Stored in `./logs/app.log` (max 10MB per file with rotation)

- **Metrics**: Available at `/actuator/prometheus` and `/actuator/metrics`

> [!NOTE]
> 
> These settings can be changed in `src/main/resources/application.properties`

## Docker Setup

There are two ways to run the API as a docker contanier 

### Option 1 - Using Docker compose (recommended)

- Docker compose will build and run a container with the same default configuration as the previous section :

```bash
# Clone the repository
git clone https://github.com/Kossay-Zemzem/game-progress-cloud-saving-API. git

# Make sure you are in the root directory of the project
cd game-progress-cloud-saving-API

#Run in attached mode
docker compose up

#Or run in detached mode 
docker compose up -d

#Stop the container
docker compose down

#Force rebuild the contanier if you change anything in source code
docker compose up --build
```

The API will be available at `http://localhost:8080`

**Configuration**

- You can override server `PORT` , and the directories of `/data` and `/logs` using:

```bash
# Linux / Mac / Git Bash
cp .env.example .env

# Windows CMD
copy .env.example .env
```

Then edit `.env` file to customize your configurationand compose will automatically use them :

```env
# Change this to the PORT you want the container to run on
APP_PORT=8080

# Change Folder paths for logs and data
LOG_PATH=./logs
DATA_PATH=./data
```

Then run

```bash
docker compose up
```

### Option 2 - Using Docker Directly

You can either pull the image from docker hub or build it locally :

#### A-Pull from Docker Hub

##### 1. Pull the Image

```bash
docker pull kossayzemzem/profile-api:latest
```

##### 2. Run the Container

Basic run (without persistence):

```bash
docker run -p 8080:8080 kossayzemzem/profile-api:latest
```

Run with persistent logs and database:

```bash
docker run -p 8080:8080 \
  -v $(pwd)/logs:/logs \
  -v $(pwd)/data:/data \
  kossayzemzem/profile-api:latest
```

For Windows PowerShell:

```powershell
docker run -p 8080:8080 `
  -v "${PWD}\logs:/logs" `
  -v "${PWD}\data:/data" `
  kossayzemzem/profile-api:latest
```

---

#### B-Build Locally

##### 1. Build the Image

```bash
docker build -t profile-api:local . 
```

##### 2. Run the Container

Basic run (without persistence):

```bash
docker run -p 8080:8080 profile-api:local
```

Run with persistent logs and database:

```bash
docker run -p 8080:8080 \
  -v $(pwd)/logs:/logs \
  -v $(pwd)/data:/data \
  profile-api:local
```

For Windows PowerShell:

```powershell
docker run -p 8080:8080 `
  -v "${PWD}\logs:/logs" `
  -v "${PWD}\data:/data" `
  profile-api:local
```

---

The API will be available at `http://localhost:8080`

## API endpoints & Example

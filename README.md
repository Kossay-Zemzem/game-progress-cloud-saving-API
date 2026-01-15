# Video Game Profile Management API - DevOps demonsrtation

**Made by** : Kossay Zemzem - Groupe C

This project is a simple backend API built with Spring Boot that allows for managing profiles of players of a video game. It acts as a backup API system to save user profiles across their devices ( Similar to how plateforms like Steam use save your progress online using Steam Cloud )

The goal of this project is demonstrate DevOps practices in a practical project.

## Official docker repository

You can checkthe [Docker hub repoistory](https://hub.docker.com/r/kossayzemzem/profile-api) or use pull the image using :

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

## Docker Setup

## API endpoints & Example

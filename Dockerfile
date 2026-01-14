FROM eclipse-temurin:17.0.11_9-jdk-ubi9-minimal AS build

# Copy Maven wrapper and config first (for caching)
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Download app dependencies
RUN ./mvnw -B dependency:go-offline

# Copy source code and build
COPY src src
RUN ./mvnw -B package -DskipTests


#Run stage which will use the JAR artefact built in the build stage
FROM eclipse-temurin:17.0.11_9-jdk-ubi9-minimal AS run

RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

COPY --from=build /target/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
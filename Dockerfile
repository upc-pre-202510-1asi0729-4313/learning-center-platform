# Stage 1: Build the Spring Boot application
FROM openjdk:24-jdk AS build
# Use a Maven image with a compatible Java version (e.g., Temurin 21)
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final runtime image
FROM eclipse-temurin:24-jre-alpine
# Use a JRE-only image for smaller footprint
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Or your application's port
ENTRYPOINT ["java", "-jar", "app.jar"]
# --- Etapa de build: Maven + JDK 24 ---
FROM maven:3-eclipse-temurin-24-alpine AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

# --- Etapa de ejecución: JRE Alpinizado con JDK 24 ---
FROM eclipse-temurin:24-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Puerto que expondrá tu app
ENV PORT=8080
EXPOSE 8080

# Iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

# -------- Etapa de build --------
FROM maven:3.9.6-eclipse-temurin-24 AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

# -------- Etapa de ejecución --------
FROM eclipse-temurin:24-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Render espera que tu app escuche en el puerto definido por PORT
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

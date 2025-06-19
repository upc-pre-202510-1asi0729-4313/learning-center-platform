# 🛠️ ETAPA DE BUILD (usa Maven con JDK 24)
FROM eclipse-temurin:24-jdk as build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN apt update && apt install -y maven && mvn clean package -DskipTests

# 🚀 ETAPA DE EJECUCIÓN (solo el JAR y el JDK 24 ligero)
FROM eclipse-temurin:24-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

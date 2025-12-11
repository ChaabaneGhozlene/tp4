# Utilise une image officielle de Java 17 (Spring Boot 3 nécessite Java 17+)
FROM eclipse-temurin:17-jdk-alpine

# Crée un répertoire pour l'application
WORKDIR /app

# Copie le jar compilé dans le conteneur
COPY target/msx-order-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port by default (override in docker-compose if needed)
EXPOSE 8082

# Command to run the service
ENTRYPOINT ["java", "-jar", "app.jar"]

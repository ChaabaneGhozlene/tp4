FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY target/Chaabane_Ghozlene_TP4_3IDL2_ServiceCatalogue-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8761

# Healthcheck corrected
HEALTHCHECK --interval=10s --timeout=5s --retries=10 \
  CMD curl -f http://localhost:8761/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
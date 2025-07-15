FROM maven:3.9.8-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Copy just the POM first to leverage Docker cache
COPY pom.xml .

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests=true -Dmaven.test.skip=true


FROM eclipse-temurin:21-jdk-alpine AS runner

WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/target/patient-service-0.0.1-SNAPSHOT.jar ./app.jar

# Expose application ports
EXPOSE 4000
EXPOSE 9000

# Add health check
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:4000/actuator/health || exit 1

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "app.jar"]

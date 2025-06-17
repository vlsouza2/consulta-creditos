# Stage 1: Build the application
FROM eclipse-temurin:11-jdk as builder
WORKDIR /desafio-back-end
# First copy only the POM file to cache dependencies
COPY pom.xml .
# Download all dependencies (cached unless POM changes)
RUN mvn dependency:go-offline -B
# Copy source code
COPY src ./src
# Build the application
RUN mvn clean package -DskipTests -B

# Stage 2: Run the application
FROM eclipse-temurin:11-jdk
WORKDIR /app
# Copy the built JAR from the builder stage
COPY --from=builder /desafio-back-end/target/desafio-api-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
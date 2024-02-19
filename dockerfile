# Stage 1: Build the application
FROM adoptopenjdk:17-jdk-hotspot AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .

# Copy the source code
COPY src src

# Build the application
RUN ./gradlew build -Pwar -x test

# Stage 2: Create the final image
FROM adoptopenjdk:17-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the built application from the builder stage
COPY --from=builder /app/build/libs/csa-back-0.0.1-SNAPSHOT.war /app/csa-back.war

# Expose the port that the application will run on (if necessary)
EXPOSE 9091

# Define the command to run your application
CMD ["java", "-jar", "csa-back.war"]

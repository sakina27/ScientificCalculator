# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Install GUI dependencies
RUN apt-get update && apt-get install -y \
    libx11-6 libxext6 libxrender1 libxtst6 libxi6 \
    libfreetype6 fontconfig \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy the compiled JAR
COPY target/ScientificCalculator-1.0-SNAPSHOT.jar app.jar

# Set DISPLAY variable for GUI
ENV DISPLAY=:0

# Prevent container from exiting
CMD ["java", "-jar", "app.jar"]

FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine 
WORKDIR /
COPY --from=builder /target/*.jar app.jar
EXPOSE 8080 
CMD ["java","-jar","/app.jar"]
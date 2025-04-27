FROM maven:3.8.5-openjdk-11-slim as build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/attendance-service-1.0.0.jar attendance-service.jar
EXPOSE 8093
ENTRYPOINT ["java", "-jar", "attendance-service.jar"]
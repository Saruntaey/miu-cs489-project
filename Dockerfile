FROM amazoncorretto:22-alpine
WORKDIR /app
COPY build/libs/my-app-1.0.1.jar server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "server.jar"]
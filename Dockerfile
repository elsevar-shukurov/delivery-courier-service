FROM eclipse-temurin:21
WORKDIR /app
COPY build/libs/ms-courier-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9092
CMD ["java", "-jar", "app.jar"]
FROM openjdk:17-oracle

WORKDIR /app

COPY target/techchallenge-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
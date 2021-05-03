FROM openjdk:latest
COPY target/EJ7-0.0.1.jar /usr/src/EJ7-0.0.1.jar
WORKDIR /usr/src/
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "EJ7-0.0.1.jar"]
FROM openjdk:latest
COPY target/EJ6-0.0.1-SNAPSHOT.jar /usr/src/EJ6-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "EJ6-0.0.1-SNAPSHOT.jar"]
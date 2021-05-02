FROM openjdk:latest
COPY target/EJ1-0.0.1-SNAPSHOT.jar /usr/src/EJ1-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "EJ1-0.0.1-SNAPSHOT.jar"]
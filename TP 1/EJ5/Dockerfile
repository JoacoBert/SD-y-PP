FROM openjdk:latest
COPY target/EJ5-0.0.1-SNAPSHOT.jar /usr/src/EJ5-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "EJ5-0.0.1-SNAPSHOT.jar"]
FROM openjdk:latest
COPY target/EJ2-0.0.1-SNAPSHOT.jar /usr/src/EJ2-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "EJ2-0.0.1-SNAPSHOT.jar"]
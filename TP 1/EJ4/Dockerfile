FROM openjdk:latest
COPY target/EJ4-0.0.1-SNAPSHOT.jar /usr/src/EJ4-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "EJ4-0.0.1-SNAPSHOT.jar"]
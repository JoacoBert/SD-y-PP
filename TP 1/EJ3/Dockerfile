FROM openjdk:latest
COPY target/EJ3-0.0.1-SNAPSHOT.jar /usr/src/EJ3-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "EJ3-0.0.1-SNAPSHOT.jar"]
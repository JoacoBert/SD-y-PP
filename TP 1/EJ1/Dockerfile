FROM openjdk:latest
COPY target/EJ1-Client.jar /usr/src/EJ1-Client.jar
WORKDIR /usr/src/
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "EJ1-Client.jar"]
FROM openjdk:latest
COPY EJ1-Server.jar  /usr/src/EJ1-Server.jar 
WORKDIR /usr/src/
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "EJ1-Server.jar"]
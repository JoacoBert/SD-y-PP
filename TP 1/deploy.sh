1 - mvn clean package

2 - docker build -t joaquinbert/tp1_ej1_server .

3 - docker push joaquinbert/tp1_ej1:latest 

4 - docker run -p 9090:9090/tcp joaquinbert/tp1_ej1_server
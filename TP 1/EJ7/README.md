## Consigna:
* Implemente un servidor RMI que resuelva tareas genéricas. Para ello tener en cuenta la interface Tarea, 	que tendrá un método ejecutar(). El objetivo es que desde el cliente se puedan escribir objetos (que implementen la interface Tarea) que hagan un cálculo concreto (calcular un número aleatorio, un primo, el valor de Pi con cierta precisión, etc.), y que esa tarea se pase al servidor RMI, quien hará el cálculo y devolverá el valor al cliente.

## Servidor:
* docker run -p 9090:9090/tcp -p 6666:6666 joaquinbert/tp1_ej7_server:latest 

## Cliente:
* docker run joaquinbert/tp1_ej7_client:latest PRIVATE_IP
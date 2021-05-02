## Consigna:
* Escribir un servicio que devuelva información de clima del lugar donde reside el servidor. Esta información podrá generarse de forma aleatoria. Deberá ser realizado con RMI. Para ello considere la interface remota, la clase (lado servidor) que implementa esa interface, el servidor, y un cliente que permita probar este funcionamiento.

## Servidor:
* docker run -p 9090:9090/tcp joaquinbert/tp1_ej4_server

## Cliente:
* nc localhost 9090
* telnet localhost 9090
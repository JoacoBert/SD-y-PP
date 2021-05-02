## Consigna:
* Escriba un servidor de mensajes en colas, que permita a los clientes dejar un mensaje (identificando de alguna forma a quién se lo dejan), y bajar los mensajes que le están dirigidos. La comunicación entre cliente y servidor debe ser mediante sockets, y el servidor debe poder atender varios clientes a la vez.

## Servidor:
* docker run -p 9090:9090/tcp joaquinbert/tp1_ej3_server


## Cliente:
* nc localhost 9090
* telnet localhost 9090
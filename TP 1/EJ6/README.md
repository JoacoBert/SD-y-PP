## Consigna:
* Escribir un servidor utilizando RMI, que ofrezca la posibilidad de sumar y restar vectores de enteros. Introduzca un error en su código que modifique los vectores recibidos por parámetro. Qué impacto se genera? Qué conclusión saca sobre la forma de pasaje de parámetros en RMI? Mostrar claramente los valores de los vectores del lado cliente, antes y después de la ejecución de la suma o resta.

## Servidor:
* docker run -p 9090:9090/tcp -p 6666:6666 joaquinbert/tp1_ej6_server:latest 

## Cliente:
* docker run joaquinbert/tp1_ej6_client:latest PRIVATE_IP
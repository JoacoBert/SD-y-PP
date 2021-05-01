package TP1_Ej2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHilo2 implements Runnable {
    BufferedReader canalEntrada;
    PrintWriter canalSalida;
    Socket client;
    

    public ServerHilo2(Socket client) {

        this.client = client;
    }

    @Override
    public void run() {
        try {
            BufferedReader canalEntrada = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            String mensajeClient = canalEntrada.readLine();

            System.out.println("Mensaje del cliente " + this.client.getPort() + ": " + mensajeClient);
            //log.info("Mensaje del cliente " + this.client.getPort() + ": " + mensajeClient);

            PrintWriter canalSalida = new PrintWriter(this.client.getOutputStream(), true);
            Thread.sleep(1000);
            canalSalida.println("Usted ha escrito el siguiente mensaje: " + mensajeClient);
            Thread.sleep(2000);
            System.out.println("Cerrando conexion con el cliente " + this.client.getPort() );
           // log.info("Cerrando Server...");
            client.close();
        }catch (Exception e){

        }
    }
}


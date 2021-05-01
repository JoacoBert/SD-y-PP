package TP1_Ej2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private Socket clientSocket;
	private PrintWriter canalSalida;
	private BufferedReader canalEntrada;
    DataOutputStream salida;
    DataInputStream entrada;
    Scanner teclado = new Scanner(System.in);
    String mensajeRecibido;

    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
		clientSocket = new Socket(ip, port);
		canalSalida = new PrintWriter(clientSocket.getOutputStream(), true);
		canalEntrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

    public void stopConnection() {
        try {
			canalEntrada.close();
			canalSalida.close();
	        clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


    public String sendMessage(String msg) {
            String resp = null;
            canalSalida.println(msg);
            try {
                resp = canalEntrada.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return resp;   
    }

    public int welcomeMessage(){
        int cantServer = 1;
        System.out.println("|| ===================================================== ||");
            System.out.println("||  Bienvenido al Servidor disponible en el puerto " + clientSocket.getPort()     + "  ||");
            System.out.println("|| ===================================================== ||");
            System.out.println("Ingrese el numero de clientes que desea generar");
            cantServer = teclado.nextInt();

            return cantServer;
    }

    public static void main(String args[]) throws UnknownHostException, IOException, InterruptedException {
		Client client = new Client();
        client.startConnection("127.0.0.1", 9090);
        int cantidadServidores = client.welcomeMessage();
        
        for (int i = 0; i < cantidadServidores; i++) {
            client.startConnection("127.0.0.1", 9090);
            client.sendMessage("Mensaje Nº " + (i + 1) );
            client.stopConnection();
        }    
    }
}

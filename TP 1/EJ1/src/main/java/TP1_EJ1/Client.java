package TP1_EJ1;

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

    public String sendMessage() throws IOException, InterruptedException {
    
            String mensajeAlServer = "";
            
            System.out.println("|| ===================================================== ||");
            System.out.println("||  Bienvenido al Servidor disponible en el puerto " + clientSocket.getPort()     + "  ||");
            System.out.println("|| ===================================================== ||");
            System.out.println("Ingrese un mensaje y el mismo será reenviado");
            mensajeAlServer = teclado.nextLine();
            canalSalida.println(mensajeAlServer);
          
            BufferedReader canalEntrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String mensajeRecibido = canalEntrada.readLine(); // Para saltear cartel de bienvenida
            String mensajeRecibido1 = canalEntrada.readLine();
            String mensajeRecibido2 = canalEntrada.readLine();
            String mensajeRecibido3 = canalEntrada.readLine();
            String respuestaServer = canalEntrada.readLine();



            System.out.println("RESPUESTA DEL SERVER: " + respuestaServer);

		try {
			respuestaServer = canalEntrada.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return respuestaServer;
    }

    public static void main(String args[]) throws UnknownHostException, IOException, InterruptedException {
		Client client = new Client();
        client.startConnection("127.0.0.1", 9090);
        client.sendMessage();
        client.stopConnection();

    }
}

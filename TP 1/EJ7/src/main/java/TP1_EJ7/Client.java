package TP1_EJ7;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import org.json.JSONObject;

import com.google.gson.Gson;

public class Client {
	
	Gson gson = new Gson();
	
    public Client (int port, String ip){
        try{
            Registry clientRMI = LocateRegistry.getRegistry(ip, port);
            System.out.println("Querying "+ip+" on port "+port);
            String[] servicesList = clientRMI.list();

            for (String service : servicesList){
                System.out.println("Service: "+service);
            }
            RemoteInt ri = (RemoteInt) clientRMI.lookup("TaskProcessorServer");

            menu(ri);

          
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    
    private String sendTask(RemoteInt ri, Tarea task) {
    	String jsonTask = gson.toJson(task);
        JSONObject obj = new JSONObject(jsonTask);
        String className = task.getClass().toString().split("class ")[1];
        obj.put("class", className);
        
        try {
			return ri.processTask(obj.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return "";
    }

    private int leerOpcion(int fromValue, int toValue) {
		int option = fromValue - 1;
        Scanner teclado = new Scanner(System.in);

		while(option < fromValue || option > toValue) {
			try {
				option = Integer.valueOf(teclado.nextInt());
			} catch (Exception e) {
				option = fromValue - 1;
			}
		}
		return option;
	}

    private void menu(RemoteInt ri) throws IOException {
		        
        Scanner teclado = new Scanner(System.in);

        System.out.println("Bienvenido. Seleccione la tarea que desea realizar");
		System.out.println("1 -- Número aleatorio.");
		System.out.println("2 -- Número primo.");
		System.out.println("3 -- Valor de Pi.");
		System.out.println("4 -- Fibonacci.");

		int option = leerOpcion(1, 5);
		switch (option) {
		case 1:
            System.out.println("Ingrese el limite superior");
            int max = teclado.nextInt();
            Tarea tarea = new GenerarAleatorio(max);
            System.out.println("Numero aleatorio generado "+sendTask(ri, tarea));
			break;
		case 2:
            Tarea tarea2 = new GenerarNumeroPrimo();
            System.out.println("Numero primo aleatorio generado entre 1 y 1000: "+sendTask(ri, tarea2));
			break;
		case 3:
            Tarea tarea3 = new CalcularPI();
            System.out.println("Pi: "+sendTask(ri, tarea3));
			break;
        case 4: 
            System.out.println("Ingrese el numero que desea calcular");
            int fibo = teclado.nextInt();
            Tarea tarea4 = new Fibonacci(fibo);
            System.out.println("Resultado Fibonacci: "+sendTask(ri, tarea4));
            break;  
		}
	}

    public static void main( String[] args )
    {
        int port = 9090;
        String ip = "127.0.0.1";
        if(args.length == 1)
        	ip = args[0];
        new Client(port, ip);
    }
}

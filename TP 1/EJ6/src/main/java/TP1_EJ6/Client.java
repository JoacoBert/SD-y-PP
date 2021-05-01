package TP1_EJ6;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

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
            RemoteInt ri = (RemoteInt) clientRMI.lookup("VectorSumServer");

            System.out.println("Vector de Suma/Resta de vectores");

            Vector v1 = new Vector(cargarVectores(1));
            Vector v2 = new Vector(cargarVectores(2));
            
            String jsonV1 = gson.toJson(v1, Vector.class);
            String jsonV2 = gson.toJson(v2, Vector.class);

            String jsonVM1 = ri.modificarVector(jsonV1);
            String jsonVM2 = ri.modificarVector(jsonV2);
            
            String jsonVectorResultadoSuma = ri.suma(jsonVM1, jsonVM2);
            String jsonVectorResultadoResta = ri.resta(jsonVM1, jsonVM2);

            Vector vectorResultadoSuma = gson.fromJson(jsonVectorResultadoSuma, Vector.class);
            Vector vectorResultadoResta = gson.fromJson(jsonVectorResultadoResta, Vector.class);

            System.out.println("Vector 1: " + v1);
            System.out.println("Vector 2: " + v2);

            System.out.println("Vector Suma: " + vectorResultadoSuma);
            System.out.println("Vector Resta: " + vectorResultadoResta);
            
            
            
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static int[] cargarVectores(int number){

        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese 5 valores para el vector " + number);

        int[] v1 = new int[5];

        for (int i = 0; i < 5; i++) {
            v1[i]=teclado.nextInt();
        }

        return v1;
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

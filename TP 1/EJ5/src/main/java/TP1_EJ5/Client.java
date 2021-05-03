package TP1_EJ5;


import com.google.gson.Gson;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    Gson gson = new Gson();

    public Client (int port, String ip){
        try{
            Registry clientRMI = LocateRegistry.getRegistry(ip, port);
            String[] servicesList = clientRMI.list();

            for (String service : servicesList){
                System.out.println(" Service: " + service);
            }
            RemoteInt ri = (RemoteInt) clientRMI.lookup("WeatherServer");
            String weather = ri.getWeather("BSAS");
            System.out.println("WeatherServer: " + weather);
        }catch (Exception e){
            e.printStackTrace();
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

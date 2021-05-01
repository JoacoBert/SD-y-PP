package TP1_EJ6;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server {
	public Server(int port){
        try{
        	Registry serverRMI = LocateRegistry.createRegistry(port); 
        	
            System.out.println ("RMI Registry has been started on port: "+port);
            
            ServerRunner sr = new ServerRunner();
           
            RemoteInt basicService = (RemoteInt) UnicastRemoteObject.exportObject(sr, 6666);
            
            serverRMI.rebind("VectorSumServer", basicService);
        }catch (Exception e){
            e.printStackTrace();
        }
	}
        public static void main(String[] args)
        {

            int port = 9090;
            Server server = new Server(port);
       
        }
}
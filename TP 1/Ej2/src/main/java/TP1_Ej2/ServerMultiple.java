package TP1_Ej2;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMultiple {

    //private final Logger log = LoggerFactory.getLogger(Server.class);

    public ServerMultiple(int port){
        
  
        try{
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Server has started on port "+port);
            
            while(true){
               
                //log.info("Server has started on port "+ port);
            

                Socket client = ss.accept();
              
                PrintWriter canalSalida = new PrintWriter(client.getOutputStream(), true);
                
                System.out.println("Atendiendo al cliente: "+client.getPort());
                //log.info("Atendiendo al cliente: "+client.getPort());

              
                canalSalida.println("|| ===================================================== ||");
                canalSalida.println("||  Bienvenido al Servidor disponible en el puerto " + port + "  ||");
                canalSalida.println("|| ===================================================== ||");
                canalSalida.println("Ingrese un mensaje y el mismo será reenviado");

                ServerHilo2 sh = new ServerHilo2(client);
                Thread serverThread = new Thread(sh);
                serverThread.start();
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main( String[] args )
    {
        
        ServerMultiple server = new ServerMultiple(9090);
    } 
}

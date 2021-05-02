package TP1_EJ1;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


public class Server {
    //private final Logger log = LoggerFactory.getLogger(Server.class);

    public Server(int port){
        
  
        try{
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Server has started on port "+port);
  //          log.info("Server has started on port "+ port);
            

                Socket client = ss.accept();
              
                PrintWriter canalSalida = new PrintWriter(client.getOutputStream(), true);
                
                System.out.println("Atendiendo al cliente: "+client.getPort());
    //            log.info("Atendiendo al cliente: "+client.getPort());

              
                canalSalida.println("|| ===================================================== ||");
                canalSalida.println("||  Bienvenido al Servidor disponible en el puerto " + port + "  ||");
                canalSalida.println("|| ===================================================== ||");
                canalSalida.println("Ingrese un mensaje y el mismo será reenviado");

                ServerHilo sh = new ServerHilo(client);
                Thread serverThread = new Thread(sh);
                serverThread.start();
                
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main( String[] args )
    {
        //String logName = Server.class.getSimpleName();
        //System.setProperty("log.name", logName);
        Server server = new Server(9090);
    } 
}

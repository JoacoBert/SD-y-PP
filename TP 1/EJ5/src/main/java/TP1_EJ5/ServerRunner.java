package TP1_EJ5;

import com.google.gson.Gson;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Random;

public class ServerRunner implements RemoteInt, Remote{
    Gson gson = new Gson();

    String[] climas = {"Nublado", "Soleado", "Lluvioso", "Tormentas fuertes", "Parcialmente nublado"};
    String[] temperaturas = {"17º", "32º", "12º", "5º", "23º", "28º"};
    String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};

    Random random = new Random();

    @Override
    public String getWeather(String city) throws RemoteException {

    
        int select = random.nextInt(climas.length);
        int select2 = random.nextInt(temperaturas.length);

        return (getDate() + ": " + climas[select] + " - " + temperaturas[select2]);
    }


    public String getDate() throws RemoteException {

        int select = random.nextInt(dias.length);
        return (dias[select]);
    }
}

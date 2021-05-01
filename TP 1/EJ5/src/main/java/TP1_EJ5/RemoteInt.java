package TP1_EJ5;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInt extends Remote {
    public String getWeather (String city) throws RemoteException;
}

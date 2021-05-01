package TP1_EJ6;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInt extends Remote {
    public String suma (String jsonV1, String jsonV2) throws RemoteException;
    
    public String resta (String jsonV1, String jsonV2) throws RemoteException;
    
    public String modificarVector (String jsonV1) throws RemoteException;
}
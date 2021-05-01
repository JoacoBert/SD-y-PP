package TP1_EJ7;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInt extends Remote {
    public String processTask (String jsonTask) throws RemoteException;
}

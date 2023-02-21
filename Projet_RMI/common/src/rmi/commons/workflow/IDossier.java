package rmi.commons.workflow;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDossier extends Remote{
	String getState()throws RemoteException;
	IDossier setState(String state)throws RemoteException; 
}

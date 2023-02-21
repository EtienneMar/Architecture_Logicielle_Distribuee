 package rmi.commons.workflow;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote{
	String getCabinetName() throws RemoteException;
	void setUsername(String name) throws RemoteException;
	void alerte(String n) throws RemoteException;
}

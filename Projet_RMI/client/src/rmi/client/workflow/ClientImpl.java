package rmi.client.workflow;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi.commons.workflow.IClient;

public class ClientImpl extends UnicastRemoteObject implements IClient{
	private String username;
	
	//Constructeur vide
	protected ClientImpl() throws RemoteException{
		super();
	}
	
	//Constructeur 
	protected ClientImpl (String name) throws RemoteException{
		this.setUsername(name);
	}
	
	//Setter
	public void setUsername(String username) throws RemoteException {
		this.username = username;
	}
	
	//Getter
	@Override
	public String getCabinetName () throws RemoteException {
		return this.username;
	}
	
	//Méthode d'alerte
	@Override
	public void alerte(String n) throws RemoteException {
		System.out.println(n);	
	}

	

}

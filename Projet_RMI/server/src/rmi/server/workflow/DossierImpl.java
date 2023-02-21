package rmi.server.workflow;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmi.commons.workflow.IDossier;

public class DossierImpl extends UnicastRemoteObject implements IDossier {

	//Attributs 
	private String state;


	//Constructeur empty 
	protected DossierImpl() throws RemoteException {}
	
	//Constructeur 
	protected DossierImpl(String state) throws RemoteException {
		this.setState(state);
	}
	
	//Méthode de la Classe
	public String toString() {
		return "L'animal est : "+state+".\n";
	}

	//Méthode Interface (getter and setter)
	@Override
	public String getState() throws RemoteException {
		return this.state;
	}

	@Override
	public IDossier setState(String state) throws RemoteException {
		this.state = state;
		return null;
	}
}

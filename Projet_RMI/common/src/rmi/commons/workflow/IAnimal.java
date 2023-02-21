package rmi.commons.workflow;

import java.rmi.Remote;
import java.rmi.RemoteException;



public interface IAnimal extends Remote{
	//Méthode récupérant Implémentation Animal
	String getName() throws RemoteException; 
	String getOwnerName() throws RemoteException; 
	String getRace() throws RemoteException; 
	String getInfoPatient() throws RemoteException;
	
	//méthode récupérant Implémentation Dossier
	IDossier getDossier() throws RemoteException;
	IDossier setDossier(String name) throws RemoteException;
	
	//méthode récupérant Implémentation d'espèce
	Species getSpecies() throws RemoteException; 
}

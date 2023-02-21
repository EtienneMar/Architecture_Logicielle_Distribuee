package rmi.commons.workflow;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ICabinet extends Remote {
	IAnimal searchPatient(String animalName) throws RemoteException;
	ArrayList<IAnimal> getPatients() throws RemoteException;
	ArrayList<IClient> getClients() throws RemoteException;
	void fullAddPatient(String name, String ownerName, String speciesName, int speciesAverageLife, String race, String state) throws RemoteException;
	void addPatient(String name, String ownerName, Species species, String race, String state) throws RemoteException;
	boolean eraseAnimal (String animalName) throws RemoteException;
	void addClient (IClient saisieUsername) throws RemoteException;
	boolean eraseClient(IClient client) throws RemoteException;
	void alert(String n) throws RemoteException;
}

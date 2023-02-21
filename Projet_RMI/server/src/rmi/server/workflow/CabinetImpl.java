package rmi.server.workflow;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import rmi.commons.workflow.IAnimal;
import rmi.commons.workflow.ICabinet;
import rmi.commons.workflow.IClient;
import rmi.commons.workflow.IDossier;
import rmi.commons.workflow.Species;

public class CabinetImpl extends UnicastRemoteObject implements ICabinet{
	private ArrayList<IAnimal> listePatient;
	private ArrayList<IClient> listeClient;
	
	//Constructeur 	
	public CabinetImpl (String nameCabinet) throws RemoteException{
		listePatient = new ArrayList<IAnimal>();
		listeClient = new ArrayList<IClient>();
	}
	
	//Méthode Recherche Animal
	@Override
	public IAnimal searchPatient(String animalName) throws RemoteException {
		for(IAnimal animal : listePatient){
            if(animal.getName().equals(animalName)) {
            	return animal;
            }               
        }
        System.err.println("Animal non reconnu");
		return null;
	}
	
	//Getter Liste IAnimal
	@Override
	public ArrayList<IAnimal> getPatients() throws RemoteException{
		return listePatient;
	}
	//Getter Liste IClient
	@Override
	public ArrayList<IClient> getClients() throws RemoteException {
		return listeClient;
	}

	//Méthode Recherche Ajouter un animal avec tout les paramètres
	@Override
	public void fullAddPatient(String name, String ownerName, String speciesName, int speciesAverageLife, String race,
			String state) 
					throws RemoteException {
		IAnimal patient = new AnimalImpl(name, ownerName, speciesName, speciesAverageLife, race, state);
		listePatient.add(patient);
		switch (listePatient.size()) {
		case 100 : this.alert("Il y a actuellement 100 patients dans le cabinet !");
		case 500 : this.alert("Il y a actuellement 500 patients dans le cabinet !");
		case 1000 : this.alert("Il y a actuellement 1000 patients dans le cabinet !");
		}
	}

	//Méthode Recherche Ajouter un animal avec tout une espèce déjà connu du client
	@Override
	public void addPatient(String name, String ownerName, Species species, String race, String state)
			throws RemoteException {
		IAnimal patient = new AnimalImpl(name, ownerName, species, race, state);
		listePatient.add(patient);
		switch (listePatient.size()) {
		case 100 : this.alert("Il y a actuellement 100 patients dans le cabinet !");
		case 500 : this.alert("Il y a actuellement 500 patients dans le cabinet !");
		case 1000 : this.alert("Il y a actuellement 1000 patients dans le cabinet !");
		}
	}
	
	//Méthode Effacer un animal de la liste 
	@Override
	public boolean eraseAnimal(String animalName) throws RemoteException {
		IAnimal animalToErase = searchPatient(animalName);
		boolean test = listePatient.remove(animalToErase);
		if (test==true) {
			switch (listePatient.size()) {
			case 100 : this.alert("Il y a actuellement 100 patients dans le cabinet !");
			case 500 : this.alert("Il y a actuellement 500 patients dans le cabinet !");
			case 1000 : this.alert("Il y a actuellement 1000 patients dans le cabinet !");
			}
		}
		return test;       
	}
	//Méthode ajouter un client à la liste Client
	@Override
	public void addClient(IClient saisieUsername)throws RemoteException{
		listeClient.add(saisieUsername);
	}
	//Méthode supprimer un client de la liste Client
	@Override
	public boolean eraseClient(IClient client) throws RemoteException {
		return listeClient.remove(client);
	}
	@Override
	public void alert (String n) throws RemoteException{
		for (IClient client : listeClient) {
			client.alerte(n);
		}
	}	
}

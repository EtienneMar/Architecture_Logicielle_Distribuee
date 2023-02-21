package rmi.server.workflow;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmi.commons.workflow.IAnimal;
import rmi.commons.workflow.IDossier;
import rmi.commons.workflow.Species;

public class AnimalImpl extends UnicastRemoteObject implements IAnimal {
	//Attributs
	private String name;
	private String ownerName;
	private Species species;
	private String race;
	private DossierImpl dossier;
	
	//Constructeur empty 
	protected AnimalImpl() throws RemoteException {}
	
	//Constructeur 
	
	protected AnimalImpl(String name, String ownerName, String speciesName, int speciesAverageLife, String race, String state)
					    throws RemoteException {
		this.name = name;
		this.ownerName = ownerName;
		this.species = new Species(speciesName, speciesAverageLife); // création de l'espèce
		this.race = race;
		this.dossier = new DossierImpl(state);
	}
	
	protected AnimalImpl(String name, String ownerName, Species species, String race, String state) 
			            throws RemoteException {
		this.name = name;
		this.ownerName = ownerName;
		this.species = species; // affectation de l'espèce créée auparavant
		this.race = race;
		this.dossier = new DossierImpl(state);
	}
	
	//Méthode Getter 
	@Override
	public String getName() throws RemoteException {
		return this.name;
	}
	
	@Override
	public String getOwnerName() throws RemoteException {
		return this.ownerName;
	}
	
	@Override
	public Species getSpecies() throws RemoteException {
		return this.species;
	}
	
	@Override
	public String getRace() throws RemoteException {
		return this.race;
	}
	
	@Override
	public IDossier getDossier() throws RemoteException {
		return this.dossier ;
	}
	
	//Méthode Setter
	
	@Override
	public IDossier setDossier(String name) throws RemoteException {
		return this.dossier.setState(name);
	}

	//ToString
	
	@Override
	public String getInfoPatient() throws RemoteException {
		return String.format("Le nom de votre Patient est %s et son maître est %s. \n"
							  + getSpecies() +"et sa race est %s.\n" +this.dossier.toString(), getName(), getOwnerName(), getRace());
	}

}
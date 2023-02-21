package rmi.commons.workflow;

import java.io.Serializable;

public class Species implements Serializable{ 
	
	//Attributs 
	private String speciesName ;
	private int speciesAverageLife;
	
	//Constructeurs empty
	public Species(){}
	
	//Constructeur
	public Species (String speciesName, int speciesAverageLife) {
		this.speciesName = speciesName;
		this.speciesAverageLife = speciesAverageLife;
	}
	
	//ToString
	public String toString() {
		return ("Le nom de son espèce est "+this.speciesName+" et l'espérance de vie moyenne de cette espèce est de : "+speciesAverageLife+" ans, ");
	}
	
	//Getter
	public String getSpeciesName(){
		return this.speciesName;
	}
	
	public int getSpeciesAverageLife() {
		return this.speciesAverageLife;
	}

	//Setter
	
	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}
	
	public void setSpeciesAverageLife (String speciesAverageLife) {
		this.speciesAverageLife = Integer.parseInt(speciesAverageLife);
	}
}
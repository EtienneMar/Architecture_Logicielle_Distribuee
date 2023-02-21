package web.service.model;

import java.util.ArrayList;


public class Hotel {
	private String nom;
	private Adresse adresse = new Adresse();
	private Etoiles nbEtoiles;
	private ArrayList<Chambre> listChambres;
	private ArrayList<infoAgence> listAgencesPartenaire; 	

	//********************** Constructeurs **************************
	public Hotel(String nomI, Etoiles etoilesI) {
		this.setNom(nomI);
		this.setEtoiles(etoilesI);
		listChambres = new ArrayList<>();
		listAgencesPartenaire = new ArrayList<>();
	}
	
	//********************** accesseurs **************************
		public String getNom() {return this.nom;}
		public void setNom(String nomI) {this.nom = nomI;}
		
		public Etoiles getEtoiles() {return this.nbEtoiles;}
		public void setEtoiles(Etoiles etoileI) {this.nbEtoiles = etoileI;}
		
		public Adresse getAdresse() {return this.adresse;}
		public void setAdresse(String pays, int numRueI, String rueI, Ville villeI) {
			this.adresse.setPays(pays);
			this.adresse.setNumRue(numRueI);
			this.adresse.setRue(rueI);
			this.adresse.setVille(villeI);
		}
		
		public ArrayList<Chambre> getListChambres() {return this.listChambres;}
		public void setListChambres(ArrayList<Chambre> listChambresI) {this.listChambres = listChambresI;}

		public void setChambreInListChambres(Chambre chambreI) {
			this.listChambres.add(chambreI);
		}
		
		public ArrayList<infoAgence> getListAgencesPartenaire() {
			return listAgencesPartenaire;
		}

		public void setListAgencesPartenaire(ArrayList<infoAgence> listAgencesPartenaire) {
			this.listAgencesPartenaire = listAgencesPartenaire;
		}		
		
	//Méthode toString modifié :
		public String toString() {
			return String.format("Hotel : %s, ", this.getNom())+this.getAdresse().toString();
		}



}

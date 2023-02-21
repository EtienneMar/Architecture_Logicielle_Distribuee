package WebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hotel {
	private String nom;
	private Adresse adresse = new Adresse();
	private Etoiles nbEtoiles;
	private ArrayList<Chambre> listChambres;
	private HashMap<String, Integer> listPartenaires;
	
	//********************** Constructeurs **************************
	public Hotel(String nomI, Etoiles etoilesI) {
		this.setNom(nomI);
		this.setEtoiles(etoilesI);
		listChambres = new ArrayList<>();
		//Ici je crée un dictionnaire avec pour chaque type de remise une liste de String qui correspondra
		//au nom de l'agence. Donc chaque hotel pour appliquer un prix différent pour chaque agence.
		listPartenaires = new HashMap<>();
	}
	
	
	//********************** accesseurs **************************
	public String getNom() {return this.nom;}
	public void setNom(String nomI) {this.nom = nomI;}
	
	public Etoiles getEtoiles() {return this.nbEtoiles;}
	public void setEtoiles(Etoiles etoileI) {this.nbEtoiles = etoileI;}
	
	public Adresse getAdresse() {return this.adresse;}
	public void setAdresse(int numRueI, String rueI, Ville villeI) {
		this.adresse.setNumRue(numRueI);
		this.adresse.setRue(rueI);
		this.adresse.setVille(villeI);
	}
	
	public ArrayList<Chambre> getListChambres() {return this.listChambres;}
	public void setListChambres(ArrayList<Chambre> listChambresI) {this.listChambres = listChambresI;}

	public void setChambreInListChambres(Chambre chambreI) {
		this.listChambres.add(chambreI);
	}
//Méthode pour associé une agence à un type de remise:
//	public void setAgenceRemise(Remise remiseI, Agence agenceI) {
//		listPartenaires.get(remiseI).add(agenceI.getNomAgence());
//	}
	public void setAgenceRemise(Agence agenceI, Integer remise) {
		listPartenaires.put(agenceI.getNomAgence(), remise);
	}
	
	public double getRemise(Agence agenceI) {
		//Vérification si l'agence est dans liste partenaire:
		if (! listPartenaires.containsKey(agenceI.getNomAgence())){
			return 0;
		}
		return listPartenaires.get(agenceI.getNomAgence());
	}
	
	
	
	
	
//Méthode toString modifié :
	public String toString() {
		return String.format("Hotel : %s, ", this.getNom())+this.getAdresse().toString();
	}
}


package rest.agence.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Offre {
	private ArrayList<String> disponibilitee = new ArrayList<>();
	
	private String nomAgence;
	
	private String adressehotel;
	
	private String nomhotel;
	
	private int etoileshotel;
	
	private int identifiant; 
	
	private int numero;
	
	private int nbLits;	
	
	private float prixChambreWithReduction;
	
	private byte[] picOfChamber;
	
	public Offre () {}

	public Offre(ArrayList<String> disponibilitee, String nomAgence, String adressehotel, String nomhotel,
			int etoileshotel, int identifiant, int numero, int nbLits, float prixChambreWithReduction,
			byte[] picOfChamber) {
		super();
		this.setDisponibilitee(disponibilitee);
		this.setNomAgence(nomAgence);
		this.setAdressehotel(adressehotel);
		this.setNomhotel(nomhotel);
		this.setEtoileshotel(etoileshotel);
		this.setIdentifiant(identifiant);
		this.setNumero(numero);
		this.setNbLits(nbLits);
		this.setPrixChambreWithReduction(prixChambreWithReduction);
		this.setPicOfChamber(picOfChamber);
	}

	public String getAdressehotel() {
		return adressehotel;
	}
	
	public void setAdressehotel(String adressehotel) {
		this.adressehotel = adressehotel;
	}


	public String getNomhotel() {
		return nomhotel;
	}

	public void setNomhotel(String nomhotel) {
		this.nomhotel = nomhotel;
	}

	public int getEtoileshotel() {
		return etoileshotel;
	}

	public void setEtoileshotel(int etoileshotel) {
		this.etoileshotel = etoileshotel;
	}

	public byte[] getPicOfChamber() {
		return picOfChamber;
	}

	public void setPicOfChamber(byte[] picOfChamber) {
		this.picOfChamber = picOfChamber;
	}

	public ArrayList<String> getDisponibilitee() {
		return disponibilitee;
	}

	public float getPrixChambreWithReduction() {
		return prixChambreWithReduction;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public String getNomAgence() {
		return nomAgence;
	}

	public void setNomAgence(String nomAgence) {
		this.nomAgence = nomAgence;
	}

	public int getNbLits() {
		return nbLits;
	}

	public void setDisponibilitee(ArrayList<String> disponibilitee) {
		this.disponibilitee = disponibilitee;
	}

	public void setPrixChambreWithReduction(float prixChambreWithReduction) {
		this.prixChambreWithReduction = prixChambreWithReduction;
	}



	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}


	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setNbLits(int nbLits) {
		this.nbLits = nbLits;
	}


	@Override
	public String toString() {
		return "Offre [disponibilitee=" + disponibilitee + ", nomAgence=" + nomAgence + ", adressehotel=" + adressehotel
				+ ", nomhotel=" + nomhotel + ", etoileshotel=" + etoileshotel + ", identifiant=" + identifiant
				+ ", numero=" + numero + ", nbLits=" + nbLits + ", prixChambreWithReduction=" + prixChambreWithReduction
				+ ", picOfChamber=" + picOfChamber + "]";
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(picOfChamber);
		result = prime * result + Objects.hash(adressehotel, disponibilitee, etoileshotel, identifiant, nbLits,
				nomAgence, nomhotel, numero, prixChambreWithReduction);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offre other = (Offre) obj;
		return Objects.equals(adressehotel, other.adressehotel) && Objects.equals(disponibilitee, other.disponibilitee)
				&& etoileshotel == other.etoileshotel && identifiant == other.identifiant && nbLits == other.nbLits
				&& Objects.equals(nomAgence, other.nomAgence) && Objects.equals(nomhotel, other.nomhotel)
				&& numero == other.numero && Arrays.equals(picOfChamber, other.picOfChamber)
				&& Float.floatToIntBits(prixChambreWithReduction) == Float
						.floatToIntBits(other.prixChambreWithReduction);
	}

}




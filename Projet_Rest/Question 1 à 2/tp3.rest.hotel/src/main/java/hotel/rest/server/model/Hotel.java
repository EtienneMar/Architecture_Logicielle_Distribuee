package hotel.rest.server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel")
public class Hotel {
	
	//Attributs
	@Id
	@Column(name = "nom")
	private String nom;

	@Column(name = "etoiles")
	private int etoiles;

	@Column(name = "pays")
	private String pays;

	@Column(name = "ville")
	private String ville;

	@Column(name = "num_rue")
	private int numRue;

	@Column(name = "rue")
	private String rue;

	@Column(name = "longitude")
	private float longitude;

	@Column(name = "lattitude")
	private float lattitude;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "hotel")
	@JsonManagedReference
	private List<Chambre> listChambres = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "hotel")
	@JsonManagedReference
	private List<AgencePartenaire> agencesPartenaire = new ArrayList<>();

	// ********************** Constructeurs **************************

	public Hotel() {
	}

	public Hotel(String nom, int etoiles, String pays, String ville, int numRue, String rue, float longitude,
			float lattitude) {
		this.setNom(nom);
		this.setEtoiles(etoiles);
		this.setPays(pays);
		this.setVille(ville);
		this.setNumRue(numRue);
		this.setRue(rue);
		this.setLongitude(longitude);
		this.setLattitude(lattitude);
	}

	// ********************** accesseurs **************************

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getEtoiles() {
		return etoiles;
	}

	public void setEtoiles(int etoiles) {
		this.etoiles = etoiles;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getNumRue() {
		return numRue;
	}

	public void setNumRue(int numRue) {
		this.numRue = numRue;
	}

	public String getRue() {
		return this.rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLattitude() {
		return lattitude;
	}

	public void setLattitude(float lattitude) {
		this.lattitude = lattitude;
	}

	public List<AgencePartenaire> getAgencesPartenaire() {
		return agencesPartenaire;
	}

	public void setAgencesPartenaire(List<AgencePartenaire> agencesPartenaire) {
		this.agencesPartenaire = agencesPartenaire;
	}

	public List<Chambre> getListChambres() {
		return listChambres;
	}

	public void setListChambres(List<Chambre> listChambres) {
		this.listChambres = listChambres;
	}

	//M??thodes
	@Override
	public String toString() {
		return "Hotel [nom=" + nom + ", etoiles=" + etoiles + ", pays=" + pays + ", ville=" + ville + ", numRue="
				+ numRue + ", rue=" + rue + ", longitude=" + longitude + ", lattitude=" + lattitude + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(agencesPartenaire, etoiles, lattitude, listChambres, longitude, nom, numRue, pays, rue,
				ville);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		return Objects.equals(agencesPartenaire, other.agencesPartenaire) && etoiles == other.etoiles
				&& Float.floatToIntBits(lattitude) == Float.floatToIntBits(other.lattitude)
				&& Objects.equals(listChambres, other.listChambres)
				&& Float.floatToIntBits(longitude) == Float.floatToIntBits(other.longitude)
				&& Objects.equals(nom, other.nom) && numRue == other.numRue && Objects.equals(pays, other.pays)
				&& Objects.equals(rue, other.rue) && Objects.equals(ville, other.ville);
	}


}

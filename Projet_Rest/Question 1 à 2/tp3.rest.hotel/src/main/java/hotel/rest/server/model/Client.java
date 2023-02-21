package hotel.rest.server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	// Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_client")
	private int id_client;

	@Column(name = "nom")
	private String nom;

	@Column(name = "prenom")
	private String prenom;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_carte_bancaire")
	private CarteBancaire carteBancaire;

	@OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "client")
	private List<Reservation> listReservation = new ArrayList<>();

//********************** Constructeurs **************************

	public Client() {
	}

	public Client(String nomI, String prenomI) {
		this.setNom(nomI);
		this.setPrenom(prenomI);
	}

	public Client(String nomI, String prenomI, CarteBancaire cb) {
		this.setNom(nomI);
		this.setPrenom(prenomI);
		this.setCarteBancaire(cb);
	}

//********************** accesseurs **************************

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public CarteBancaire getCarteBancaire() {
		return carteBancaire;
	}

	public void setCarteBancaire(CarteBancaire carteBancaire) {
		this.carteBancaire = carteBancaire;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public void setListReservation(List<Reservation> listReservation) {
		this.listReservation = listReservation;
	}

	public List<Reservation> getListReservation() {
		return listReservation;
	}

	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(carteBancaire, id_client, listReservation, nom, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(carteBancaire, other.carteBancaire) && id_client == other.id_client
				&& Objects.equals(listReservation, other.listReservation) && Objects.equals(nom, other.nom)
				&& Objects.equals(prenom, other.prenom);
	}

	
}

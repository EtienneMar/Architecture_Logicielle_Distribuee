package hotel.rest.server.model;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cartebancaire")
public class CarteBancaire {

	// Attribut
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_carte_bancaire")
	private int idCarteBancaire;

	@Column(name = "numero_carte")
	private String numeroCarte;

	@Column(name = "date_expiration")
	private String dateExpiration;

	@Column(name = "cryptogramme")
	private int cryptogramme;
	
	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	@JoinColumn(name = "id_client")
	private Client client;

	// ********************** Constructeurs **************************

	public CarteBancaire() {}

	public CarteBancaire(int identifiant, String numeroCarte, String dateExpiration, int cryptogramme) {
		this.idCarteBancaire = identifiant;
		this.numeroCarte = numeroCarte;
		this.dateExpiration = dateExpiration;
		this.cryptogramme = cryptogramme;
	}
	
	public CarteBancaire(String numeroCarte, String dateExpiration, int cryptogramme, Client client) {
		super();
		this.numeroCarte = numeroCarte;
		this.dateExpiration = dateExpiration;
		this.cryptogramme = cryptogramme;
		this.client = client;
	}
	
	public CarteBancaire(String numeroCarte, String dateExpiration, int cryptogramme) {
		this.numeroCarte = numeroCarte;
		this.dateExpiration = dateExpiration;
		this.cryptogramme = cryptogramme;
	}

	// ********************** accesseurs **************************

	public String getNumeroCarte() {
		return numeroCarte;
	}

	public void setNumeroCarte(String numeroCarte) {
		if (numeroCarte.matches("[0-9]+") && numeroCarte.length() == 16) {
			this.numeroCarte = numeroCarte;
		} else {
			System.out.println("Veuillez entrée un numéro de carte valide");
		}
	}

	public int getIdCarteBancaire() {
		return idCarteBancaire;
	}

	public void setIdCarteBancaire(int idCarteBancaire) {
		this.idCarteBancaire = idCarteBancaire;
	}

	public String getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(String dateExpiration) {

		DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM/yy");
		YearMonth today = YearMonth.now();
		YearMonth dateExpirationFormatted = YearMonth.parse(dateExpiration, monthYearFormatter);

		if (today.isBefore(dateExpirationFormatted)) {
			this.dateExpiration = dateExpiration;
		} else {
			System.out.println("Votre carte à expirer\n" + "Merci de saisir une date au format mm/yy après le "
					+ yearToStringRes(today));
		}
	}

	public int getCryptogramme() {
		return cryptogramme;
	}

	public void setCryptogramme(int cryptogramme) {
		if (cryptogramme <= 999) {
			this.cryptogramme = cryptogramme;
		} else {
			System.out.println("Merci de saisir une cryptogramme à 3 chiffre inférieur ou égal à 999");
		}
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	//Méthodes
	
	// Méthode pour transformer une YearMonth en String sous la forme MM/YYYY
	public String yearToStringRes(YearMonth yearMonthI) {
		DateTimeFormatter DateToString = DateTimeFormatter.ofPattern("MM/yyyy");
		return yearMonthI.format(DateToString);
	}

	@Override
	public String toString() {
		return "CarteBancaire [idCarteBancaire=" + idCarteBancaire + ", numeroCarte=" + numeroCarte
				+ ", dateExpiration=" + dateExpiration + ", cryptogramme=" + cryptogramme + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cryptogramme, dateExpiration, idCarteBancaire, numeroCarte);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarteBancaire other = (CarteBancaire) obj;
		return cryptogramme == other.cryptogramme && Objects.equals(dateExpiration, other.dateExpiration)
				&& idCarteBancaire == other.idCarteBancaire && Objects.equals(numeroCarte, other.numeroCarte);
	}
	
	

}
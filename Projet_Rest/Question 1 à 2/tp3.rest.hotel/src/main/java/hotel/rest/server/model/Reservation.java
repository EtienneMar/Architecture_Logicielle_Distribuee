package hotel.rest.server.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {

	//Attributs
	@Id
	@Column(name = "id_reservation")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReservation = 0;
	

	@ManyToOne(targetEntity = Chambre.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)

        @JoinColumn(name = "idChambre", referencedColumnName = "id_chambre")
       
   
    @JsonBackReference
	private Chambre chambre = null;

//	@ManyToOne(targetEntity = Hotel.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name ="id_nom_hotel", foreignKey = @ForeignKey(name = "FK_nom_Hotel", foreignKeyDefinition = "foreign key reservation(id_nom_hotel) references hotel (nom)"))
//	private Hotel hotel;

	@Column(name = "date_depart")
	private LocalDate dateArrivee = LocalDate.now(); // écrire la date sous la forme : "dd-MM-yyyy"

	@Column(name = "date_arrivee")
	private LocalDate dateDepart = LocalDate.now(); // écrire la date sous la forme : "dd-MM-yyyy"

	@Column(name = "nombre_lits")
	private int nbLitsResa;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_client")
	private Client client = null;

	// ********************** Constructeurs **************************

	public Reservation() {
	}

	public Reservation(String dateArrivee, String dateDepart, int nbLitsResa) {
		this.setDateArriveeByString(dateArrivee);
		this.setDateDepartByString(dateDepart);
		this.setNbLitsResa(nbLitsResa);
	}

	public Reservation(Chambre chambre, LocalDate dateArrivee, LocalDate dateDepart, int nbLitsResa) {
		this.chambre = chambre;
		this.dateArrivee = dateArrivee;
		this.dateDepart = dateDepart;
		this.nbLitsResa = nbLitsResa;
	}

	// ********************** accesseurs **************************

	public Reservation(LocalDate now, LocalDate plusDays, int i) {
		this.setDateArrivee(now);
		this.setDateDepart(plusDays);
		this.setNbLitsResa(i);
	}

	public void setDateDepart(LocalDate dateDepart) {
		this.dateDepart = dateDepart;
	}

	public int getIdReservation() {
		return idReservation;
	}

	public Chambre getChambre() {
		return chambre;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

	public int getNbLitsResa() {
		return this.nbLitsResa;
	}

	public void setNbLitsResa(int nbLitsresaI) {
		this.nbLitsResa = nbLitsresaI;
	}

	public LocalDate getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(LocalDate dateArrivee) {
		this.dateArrivee = dateArrivee;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client clientI) {
		this.client = clientI;
	}

	public void setDateArriveeByString(String dateArrivee) {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateFormatedArr = LocalDate.parse(dateArrivee, dateTimeFormatter);
		if (today.isBefore(dateFormatedArr) || today.equals(dateFormatedArr)) {
			this.dateArrivee = dateFormatedArr;
		} else {
			System.out.println(
					"Merci de saisir une date au format jj-mm-yyyy, à partir du TS " + dateToStringRes(tomorrow));
		}
	}

	public LocalDate getDateDepart() {
		return dateDepart;
	}

	public void setDateDepartByString(String dateDepart) {
		LocalDate today = LocalDate.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateFormatedDepart = LocalDate.parse(dateDepart, dateTimeFormatter);
		LocalDate arrNextDay = dateFormatedDepart.plusDays(1);
		if (today.isBefore(this.dateArrivee) || this.dateArrivee.isBefore(dateFormatedDepart)) {
			this.dateDepart = dateFormatedDepart;
		} else {
			System.out.println(
					"Merci de saisir une date au format jj-mm-yyyy, à partir du " + dateToStringRes(arrNextDay));
		}
	}


	//Méthodes
	public String dateToStringRes(LocalDate dateI) {
		// pour afficher la date d'aujourd'hui au format jj-MM-yyyy en string :
		DateTimeFormatter DateToString = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return dateI.format(DateToString);
	}

	@Override
	public String toString() {
		return "Reservation [idReservation=" + idReservation + ", dateArrivee=" + dateArrivee + ", dateDepart="
				+ dateDepart + ", nbLitsResa=" + nbLitsResa + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(chambre, client, dateArrivee, dateDepart, idReservation, nbLitsResa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(chambre, other.chambre) && Objects.equals(client, other.client)
				&& Objects.equals(dateArrivee, other.dateArrivee) && Objects.equals(dateDepart, other.dateDepart)
				&& idReservation == other.idReservation && nbLitsResa == other.nbLitsResa;
	}

}

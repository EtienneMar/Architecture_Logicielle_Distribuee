package WebService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
	private LocalDate dateArrive;	//écrire la date sous la forme : "dd-MM-yyyy"
	private LocalDate dateDepart;	//écrire la date sous la forme : "dd-MM-yyyy"
	private int nbLitsResa;
	private Client client;
	
	//********************** Constructeurs **************************
	//Modif du constructeur pour prendre seulement dateArriveI,  dateDepartI,  nbLitsResaI :
	public Reservation(String dateArriveI, String dateDepartI, int nbLitsResaI) {
		this.setDateArrive(dateArriveI);
		this.setDateDepart(dateDepartI);
		this.setNbLitsResa(nbLitsResaI);
	}
	
	public Reservation(String dateArriveI, String dateDepartI, int nbLitsResaI, Client clientI) {
		this.setDateArrive(dateArriveI);
		this.setDateDepart(dateDepartI);
		this.setNbLitsResa(nbLitsResaI);
		this.setClient(clientI);
	}

	//********************** accesseurs **************************
	public int getNbLitsResa() {return this.nbLitsResa;}
	public void setNbLitsResa (int nbLitsresaI) {
		this.nbLitsResa = nbLitsresaI;
	}
	
	
	
	public LocalDate getDateArrive() {return dateArrive;}
	public void setDateArrive(String dateArriveI) {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dateFormatedArr = LocalDate.parse(dateArriveI, dateTimeFormatter);
		if (today.isBefore(dateFormatedArr)) {
			this.dateArrive = dateFormatedArr;
		}
		else {
//			DateTimeFormatter DateToString = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//			String tomorrowStr =  tomorrow.format(DateToString);
			System.out.println("Merci de saisir une date au format jj-mm-yyyy, à partir du " + dateToStringRes(tomorrow));
		}
	}

	public LocalDate getDateDepart() {return dateDepart;}

	public void setDateDepart(String dateDepartI) {
		LocalDate today = LocalDate.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dateFormatedDepart = LocalDate.parse(dateDepartI, dateTimeFormatter);
		//arrNextDay = date de départ minimum (lendemain de l'arrivée)
		LocalDate arrNextDay = dateFormatedDepart.plusDays(1);
		if (today.isBefore(this.dateArrive) && this.dateArrive.isBefore(dateFormatedDepart)){
			this.dateDepart = dateFormatedDepart;
		}
		else {
			System.out.println("Merci de saisir une date au format jj-mm-yyyy, à partir du " + dateToStringRes(arrNextDay));
		}
	}

	public Client getClient() {return client;}

	//Ici quand je renseigne le clien, j'ajoute également la réservation à la liste de éservation du client
	public void setClient(Client clientI) {
		this.client = clientI;
		clientI.setResaInListResaClient(this);
	}
	//Méthode pour transformer une LocalDate en String sous la forme jj-mm-aaaa
	public String dateToStringRes(LocalDate dateI) {
		//pour afficher la date d'aujourd'hui au format jj-MM-yyyy en string :
		DateTimeFormatter DateToString = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return dateI.format(DateToString);
	}
	//methode toString() modifé:
	public String toString() {
		String str="[";
		if (this.getClient() != null) {
			str += String.format("%s, nombre de lits : %d, ",this.getClient().getNom(), this.getNbLitsResa());
		}
		str += String.format("date d'arrivé : %s, date départ : %s]"
				, dateToStringRes(this.getDateArrive()), dateToStringRes(this.getDateDepart()));
		return str;
	}
	
}

package WebService;

import java.util.ArrayList;

public class Client {
	private String nom;
	private String prenom;
	private ArrayList<Reservation> listReservationsClient;
	
//********************** Constructeurs **************************
	public Client(String nomI, String prenomI) {
		this.setNom(nomI);
		this.setPrenom(prenomI);
		this.listReservationsClient = new ArrayList<>();
	}
	
	public Client(String nomI, String prenomI,ArrayList<Reservation> listReservationsI) {
		this.setNom(nomI);
		this.setPrenom(prenomI);
		this.setListReservationsClient(listReservationsI);
	}
	
//********************** accesseurs **************************
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	
	public ArrayList<Reservation> getListReservationsClient() {
		return listReservationsClient;
	}
	public void setListReservationsClient(ArrayList<Reservation> listReservationsClient) {
		if (listReservationsClient == null) {
			this.listReservationsClient = listReservationsClient;
		}
		else {
			System.err.println("Le liste de réservation n'a pas été créée car il existe déjà une liste de réservation");
		}
	}
	public void setResaInListResaClient(Reservation reservationI) {
		this.listReservationsClient.add(reservationI);
	}

	
	

}

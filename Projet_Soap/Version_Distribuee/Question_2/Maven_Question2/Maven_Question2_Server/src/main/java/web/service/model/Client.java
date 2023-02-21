package web.service.model;

import java.util.ArrayList;


public class Client {
	private String nom;
	private String prenom;
	private ArrayList<Reservation> listReservationsClient;
	private CarteBancaire carteBancaire = new CarteBancaire(); 

	
//********************** Constructeurs **************************
	

	//On garde ces méthodes pour les clients crée en brut au début du main c'est à supprimer dans le futur
	
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
	
	//Vrai méthode
	public Client(String nomI, String prenomI, CarteBancaire carteBancaire) {
		this.setNom(nomI);
		this.setPrenom(prenomI);
		this.listReservationsClient = new ArrayList<>();
		this.setCarteBancaire(carteBancaire);
	}
	
	
	public Client (String nomI, String prenomI,ArrayList<Reservation> listReservationsI, CarteBancaire carteBancaire) {
		this.setNom(nomI);
		this.setPrenom(prenomI);
		this.setListReservationsClient(listReservationsI);
		this.setCarteBancaire(carteBancaire);
	}
	
//********************** accesseurs **************************
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	
	public ArrayList<Reservation> getListReservationsClient() {return listReservationsClient;}
	
	public void setListReservationsClient(ArrayList<Reservation> listReservationsClient) {
		if (listReservationsClient == null) {
			this.listReservationsClient = listReservationsClient;
		}
		else {System.err.println("Le liste de réservation n'a pas été créée car il existe déjà une liste de réservation");}
	}
	
	public CarteBancaire getCarteBancaire() {
		return carteBancaire;
	}

	public void setCarteBancaire(CarteBancaire carteBancaire) {
		this.carteBancaire = carteBancaire;
	}

	public void setResaInListResaClient(Reservation reservationI) {
		this.listReservationsClient.add(reservationI);
	}

}

package WebService;

import java.util.ArrayList;

public class Chambre {
	private int numero;
	private int nbLits;
	private Hotel hotel;
	private int prixChambre;
	private ArrayList<Reservation> listReservationsChambre = new ArrayList<>();
	
	//********************** Constructeurs **************************
	public Chambre(int numeroI, int nbLitsI, int prixChambreI, Hotel hotelI) {
		this.setNumero(numeroI);
		this.setNbLits(nbLitsI);
		this.setPrixChambre(prixChambreI);
		this.setHotel(hotelI);
	}
	
	//********************** accesseurs **************************
	public int getNumero() {return this.numero;}
	public void setNumero (int numeroI) {
		if (numeroI > 1) {
			this.numero = numeroI;
		}
		else{
			System.err.println("Le numéro de rue doit être supérieur à 0");
		}
	}
	
	public int getNbLits() {return this.nbLits;}
	public void setNbLits(int nbLitsI) {
		if (nbLitsI > 0) {
			this.nbLits = nbLitsI;
		}
		else{
			System.err.println("Le nombre de lits doit être supérieur à 0");
		}
	}
	
	
	public int getPrixChambre() {return this.prixChambre;}
	public void setPrixChambre(int prixChambreI) {
		if (prixChambreI > 0) {
			this.prixChambre = prixChambreI;
		}
		else {
			System.err.println("Le prix de la chambre doit être supérieur à 0");
		}
	}

	//Ici dès que la chambre est créée et associé à un hotel, la chambre est automatiquement ajouté à la liste de chambre de l'hotel.
	public Hotel getHotel() {return this.hotel;}
	public void setHotel(Hotel hotelI) {
		this.hotel = hotelI;
		this.hotel.setChambreInListChambres(this); 
	}
	
	public ArrayList<Reservation> getListReservationsChambre(){return this.listReservationsChambre;}
	public void setListReservationsChambre(ArrayList<Reservation> listReservationsChambreI) {
		this.listReservationsChambre = listReservationsChambreI;
	}
	public void setReservationInListReservation(Reservation reservationI) {
		this.listReservationsChambre.add(reservationI);
	}
	
	//Méthode toString modifié :
	public String toString() {
		String str = String.format("Hotel : %s, Chambre n°%d", this.hotel.getNom(), this.getNumero());
		return str;
	}
}

package web.service.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class Offre extends Chambre{

	public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private ArrayList<String> disponibilitee = new ArrayList<>();
	private String hotelNom;
	private float prixChambreWithReduction;

	public Offre(float coeffPromotion, Chambre chambre) {
		super(chambre.getIdentifiant(), chambre.getNumero(), chambre.getNbLits(), chambre.getPicOfChamberBytesCode());
		setHotelNom(chambre.getHotel().getNom());
		setDisponibiliteeWithArrayList(chambre.getListReservationsChambre());
		setPrixChambreWithReduction(chambre.getPrixChambre()*coeffPromotion);
	}
	
	public ArrayList<String> getDisponibilitee() {
		return disponibilitee;
	}
	
	public void setDisponibilitee(ArrayList<String> disponibilitee) {
		this.disponibilitee = disponibilitee;
	}

	public String getHotelNom() {
		return hotelNom;
	}

	public void setHotelNom(String hotelNom) {
		this.hotelNom = hotelNom;
	}
	
	public void setPrixChambreWithReduction(float prixChambreWithReduction) {
		this.prixChambreWithReduction = Math.round(prixChambreWithReduction);
	}

	public float getPrixChambreWithReduction() {
		return prixChambreWithReduction;
	}

	
	public void setDisponibiliteeWithArrayList(ArrayList<Reservation> reservation) {
		LocalDate today = LocalDate.now();
		LocalDate end = LocalDate.of(today.getYear()+1,today.getMonthValue() , today.getDayOfMonth());
		
		if (reservation.isEmpty()) {
			this.disponibilitee.add(today.format(dateTimeFormatter));
			this.disponibilitee.add(end.format(dateTimeFormatter));
		}else {
			ArrayList<LocalDate> localDateDisponibilitee = new ArrayList<>();
			localDateDisponibilitee.add(end);
			localDateDisponibilitee.add(today);
			for (int i = 0; i < reservation.size(); i++)
			{
				localDateDisponibilitee.add(reservation.get(i).getDateArrivee().minusDays(1));
				localDateDisponibilitee.add(reservation.get(i).getDateDepart().plusDays(1));
			}
			Collections.sort(localDateDisponibilitee);
			for (int j = 0; j < localDateDisponibilitee.size(); j++) {
				this.disponibilitee.add(localDateDisponibilitee.get(j).format(dateTimeFormatter)); 
			}
		}
			
	}
	@Override
	public String toString() {
		return "Offre [disponibilitee=" + disponibilitee + ", hotelNom=" + hotelNom + ", getIdentifiant()="
				+ getIdentifiant() + ", getNumero()=" + getNumero() + ", getNbLits()=" + getNbLits()
				+ ", getPrixChambre()=" + getPrixChambre() + "]";
	}







}

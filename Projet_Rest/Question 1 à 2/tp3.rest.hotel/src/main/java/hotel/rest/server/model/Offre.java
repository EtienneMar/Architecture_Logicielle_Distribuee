package hotel.rest.server.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

public class Offre extends Chambre {

	//Attributs
	public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private ArrayList<String> disponibilitee = new ArrayList<>();
	private String nomHotel;
	private float prixChambreWithReduction;
	private int identifiant;
	private byte[] picOfChamber;

	//Constructeur
	public Offre(float coeffPromotion, Chambre chambre) throws IOException {
		super(chambre.getNumero(), chambre.getNbLits(), chambre.getPrixChambre(),
				chambre.getPicOfChamberByPathPicture());
		setIdentifiant(chambre.getIdChambre());
		setDisponibiliteeWithArrayList(new ArrayList<Reservation>(chambre.getListReservation()));
		setNomHotel(chambre.getHotel().getNom());
		setPrixChambreWithReduction(chambre.getPrixChambre() * coeffPromotion);
		setPicOfChamberByPathPicture(chambre.getPicOfChamberByPathPicture());
	}

	//Accesseur
	public String getNomHotel() {
		return nomHotel;
	}

	public byte[] getPicOfChamber() {
		return picOfChamber;
	}

	public void setNomHotel(String nomHotel) {
		this.nomHotel = nomHotel;
	}

	public void setPicOfChamber(byte[] picOfChamber) {
		this.picOfChamber = picOfChamber;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public ArrayList<String> getDisponibilitee() {
		return disponibilitee;
	}

	public void setDisponibilitee(ArrayList<String> disponibilitee) {
		this.disponibilitee = disponibilitee;
	}

	public void setPrixChambreWithReduction(float prixChambreWithReduction) {
		this.prixChambreWithReduction = Math.round(prixChambreWithReduction);
	}

	public float getPrixChambreWithReduction() {
		return prixChambreWithReduction;
	}

	//méthodes
	public void setDisponibiliteeWithArrayList(ArrayList<Reservation> reservation) {
		LocalDate today = LocalDate.now();
		LocalDate end = LocalDate.of(today.getYear() + 1, today.getMonthValue(), today.getDayOfMonth());

		if (reservation.isEmpty()) {
			this.disponibilitee.add(today.format(dateTimeFormatter));
			this.disponibilitee.add(end.format(dateTimeFormatter));
		} else {
			ArrayList<LocalDate> localDateDisponibilitee = new ArrayList<>();
			localDateDisponibilitee.add(end);
			localDateDisponibilitee.add(today);
			for (int i = 0; i < reservation.size(); i++) {
				localDateDisponibilitee.add(reservation.get(i).getDateArrivee().minusDays(1));
				localDateDisponibilitee.add(reservation.get(i).getDateDepart().plusDays(1));
			}
			Collections.sort(localDateDisponibilitee);
			for (int j = 0; j < localDateDisponibilitee.size(); j++) {
				this.disponibilitee.add(localDateDisponibilitee.get(j).format(dateTimeFormatter));
			}
		}

	}

	public void setPicOfChamberByPathPicture(String pathPicure) throws IOException {
		File fichier = new File(pathPicure);
		BufferedImage fileToByteCode = ImageIO.read(fichier);
		ByteArrayOutputStream ByteForXML = new ByteArrayOutputStream();
		ImageIO.write(fileToByteCode, "jpg", ByteForXML);
		this.picOfChamber = ByteForXML.toByteArray();
	}

	@Override
	public String toString() {
		return "Offre [disponibilitee=" + disponibilitee + ", prixChambreWithReduction=" + prixChambreWithReduction
				+ ", identifiant=" + identifiant + ", picOfChamber=" + picOfChamber + "]";
	}

}

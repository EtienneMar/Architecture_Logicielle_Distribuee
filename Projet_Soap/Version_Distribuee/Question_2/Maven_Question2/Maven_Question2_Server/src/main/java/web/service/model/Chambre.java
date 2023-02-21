package web.service.model;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Chambre {
	
	private int  identifiant; 
	private int numero;
	private int nbLits;
	private Hotel hotel;
	private float prixChambre;
	private byte[] picOfChamber;
	private ArrayList<Reservation> listReservationsChambre;
	
	//********************** Constructeurs *************************	
	
	public Chambre () {}	public Chambre(int identifiant, int numeroI, int nbLitsI, byte[] pictureByteCode) {
		this.setIdentifiant(identifiant);
		this.setNumero(numeroI);
		this.setNbLits(nbLitsI);
		this.setPicOfChamberBytesCode(pictureByteCode);
	}
	
	
	//Server Constructor
	public Chambre(int identifiant, int numeroI, int nbLitsI, float prixChambreI, String pathPicture, Hotel hotel) throws IOException {
		this.setIdentifiant(identifiant);
		this.setNumero(numeroI);
		this.setNbLits(nbLitsI);
		this.setPrixChambre(prixChambreI);
		this.setPicOfChamberByPathPicture(pathPicture);
		this.listReservationsChambre = new ArrayList<>();
		this.setHotel(hotel);
	}
	
	//********************** accesseurs **************************
	


	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	
	public int getNumero() {return this.numero;}
	public void setNumero (int numeroI) {
		if (numeroI >= 1) {
			this.numero = numeroI;
		}
		else{
			System.err.println("Le num�ro de la chambre doit �tre sup�rieur � 0");
		}
	}
	
	public int getNbLits() {return this.nbLits;}
	public void setNbLits(int nbLitsI) {
		if (nbLitsI > 0) {
			this.nbLits = nbLitsI;
		}
		else{
			System.err.println("Le nombre de lits doit �tre sup�rieur � 0");
		}
	}
	
	
	public float getPrixChambre() {return this.prixChambre;}
	public void setPrixChambre(float prixChambreI) {
		if (prixChambreI >0.0) {
			this.prixChambre = prixChambreI;
		}
		else {
			System.err.println("Le prix de la chambre doit �tre sup�rieur � 0");
		}
	}

	//Ici d�s que la chambre est cr��e et associ� � un hotel, la chambre est automatiquement ajout� � la liste de chambre de l'hotel.


	public void setHotel(Hotel hotelI) {
		this.hotel = hotelI;
	}
	
	public Hotel getHotel() {
		return this.hotel;
	}
	
	
	public ArrayList<Reservation> getListReservationsChambre(){return this.listReservationsChambre;}
	public void setListReservationsChambre(ArrayList<Reservation> listReservationsChambreI) {
		this.listReservationsChambre = listReservationsChambreI;
	}
	public void setReservationInListReservation(Reservation reservationI) {
		this.listReservationsChambre.add(reservationI);
	}
	
	public byte[] getPicOfChamberBytesCode() {
		return picOfChamber;
	}

	public void setPicOfChamberBytesCode(byte[] picOfChamberBytesCode) {
		this.picOfChamber = picOfChamberBytesCode;
	}
	
	public void setPicOfChamberByPathPicture(String pathPicure) throws IOException {
		File fichier = new File(pathPicure);
		BufferedImage fileToByteCode = ImageIO.read(fichier);
		ByteArrayOutputStream ByteForXML = new ByteArrayOutputStream();
		ImageIO.write(fileToByteCode, "jpg", ByteForXML);
		this.picOfChamber = ByteForXML.toByteArray();	
	}
		
	//M�thode toString modifi� :
	public String toString() {
		String str = String.format("Chambre n�%d", this.getNumero());
		return str;
	}
	
}
		


package web.service.services;


import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import web.service.model.Hotel;


@WebService(endpointInterface = "web.service.services.IWebService2Reservation")

public class WebService2ReservationImpl implements IWebService2Reservation{
	
	private HotelRepository hotel ;
	
	public WebService2ReservationImpl(HotelRepository hotel) {
		this.hotel = hotel;
	}
	
	@Override
	public String doReservation(int identifiantAgence, String loginAgence, String passwordAgence, int idOffre, String prenomClient, String nomClient,
			String numeroCarte, int cryptogramme, String cardDateExpiration, String dateDebut, String dateFin,
			int nbLits) {
		return hotel.doReservation(identifiantAgence, loginAgence, passwordAgence, idOffre, prenomClient, nomClient, numeroCarte, cryptogramme, cardDateExpiration, dateDebut, dateFin, nbLits);
	}

	@Override
	public ArrayList<Integer> listIdentifiantOffre() {
		return hotel.getIdentifiantOffre();
	}

	@Override
	public String getNomHotel() {
		return hotel.getNomHotel();
	}


	
}
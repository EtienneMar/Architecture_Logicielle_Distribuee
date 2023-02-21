package web.service.services;



import java.util.ArrayList;

import javax.jws.WebService;

import web.service.model.Chambre;
import web.service.model.Client;
import web.service.model.Etoiles;
import web.service.model.Hotel;
import web.service.model.Offre;
import web.service.model.Ville;



@WebService(endpointInterface = "web.service.services.IWebService1Consultation")

public class WebService1ConsultationImpl implements IWebService1Consultation {
	
	
	private HotelRepository hotel ;
	
	public WebService1ConsultationImpl(HotelRepository hotel) {	
		this.hotel = hotel;
	}
	
	


	@Override
	public String getAdress() {
		return hotel.getAddressHotel();
	}
	


	@Override
	public String getPays() {
		return hotel.getPays();
	}


	//WebService 1
	@Override
	public ArrayList<Offre> consultationDisponiblite(int identifiantAgence, String loginAgence, String dateDebut,
			String dateFin, int nbPersonne, String pays, Ville ville, Etoiles etoiles, float prixMin, float prixMax) {
		
		return hotel.consultationDisponiblite(identifiantAgence, loginAgence, dateDebut, dateFin, nbPersonne, pays, ville, etoiles, prixMin, prixMax);

	}




	@Override
	public String getHotelNom() {
		// TODO Auto-generated method stub
		return hotel.getNomHotel();
	}	




}
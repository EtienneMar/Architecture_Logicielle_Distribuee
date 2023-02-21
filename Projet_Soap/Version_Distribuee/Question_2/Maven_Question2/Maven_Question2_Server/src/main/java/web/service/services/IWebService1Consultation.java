package web.service.services;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

import web.service.model.Chambre;
import web.service.model.Etoiles;
import web.service.model.Offre;
import web.service.model.Ville;
import web.service.model.Client;

@WebService
public interface IWebService1Consultation {
	

	@WebMethod
	String getHotelNom();
	
	@WebMethod
	String getAdress();
	
	@WebMethod
	String getPays(); 
	
	//WebService 1
	@WebMethod
	ArrayList<Offre> consultationDisponiblite(int identifiantAgence, String loginAgence, String dateDebut, String dateFin, int nbPersonne, String pays,
			Ville ville, Etoiles etoiles, float prixMin, float prixMax); 

}


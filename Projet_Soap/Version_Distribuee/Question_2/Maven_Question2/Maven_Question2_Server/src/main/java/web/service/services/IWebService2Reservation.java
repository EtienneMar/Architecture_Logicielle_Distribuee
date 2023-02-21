package web.service.services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IWebService2Reservation {
	
	@WebMethod
	ArrayList<Integer> listIdentifiantOffre();
	
	@WebMethod
	String getNomHotel();
	
	//WebService 2
	@WebMethod
	String doReservation(int identifiantAgence, String loginAgence, String passwordAgence, int idOffre, String prenomClient, String nomClient,
			String numeroCarte, int cryptogramme, String cardDateExpiration, String dateDebut, String dateFin,
			int nbLits);

}

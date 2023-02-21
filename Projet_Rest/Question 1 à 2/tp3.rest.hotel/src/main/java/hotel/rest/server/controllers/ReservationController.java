package hotel.rest.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hotel.rest.server.model.CarteBancaire;
import hotel.rest.server.model.Client;
import hotel.rest.server.model.Reservation;
import hotel.rest.server.services.CarteBancaireService;
import hotel.rest.server.services.ChambreService;
import hotel.rest.server.services.ClientService;
import hotel.rest.server.services.ReservationService;



@RestController
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired 
	private ChambreService chambreService; 
	
	@Autowired 
	private CarteBancaireService carteBancaireService;
	
	@Autowired
	private ClientService clientService; 
	
	private static final String uri = "hotelservice/api";

	@PostMapping(uri+"/reservation")
	public String doReservation(@RequestBody MultiValueMap<String, String> reservation) {
			
			String response = "ok"; 

// 		Test Agence		
			if (reservationService.findIfReservationIsPossible(reservation.getFirst("dateArrivee"),
					reservation.getFirst("dateDepart")).isEmpty()) {
			
				Reservation reservationToSave = new Reservation(reservation.getFirst("dateArrivee"),
						reservation.getFirst("dateDepart"), Integer.parseInt(reservation.getFirst("nbLitsResa")));
				Client clientToSave = new Client(reservation.getFirst("prenomClient"),reservation.getFirst("nomClient"));

				if (!carteBancaireService.findCarteBancaireByNumeroANDcryptogrammeANDdateExpiration(reservation.getFirst("numeroCarte"), reservation.getFirst("cardDateExpiration"),
						Integer.parseInt(reservation.getFirst("cryptogramme"))).isPresent()) {

					CarteBancaire cbToSave = new CarteBancaire(reservation.getFirst("numeroCarte"),reservation.getFirst("cardDateExpiration"),
							Integer.parseInt(reservation.getFirst("cryptogramme")));
				
					clientToSave.setCarteBancaire(cbToSave);
					cbToSave.setClient(clientToSave);
					carteBancaireService.createCarteBancaire(cbToSave);
					
				} else {

					CarteBancaire cbAlreadyBind = carteBancaireService.findCarteBancaireByNumeroANDcryptogrammeANDdateExpiration(
									reservation.getFirst("numeroCarte"), reservation.getFirst("cardDateExpiration"),
									Integer.parseInt(reservation.getFirst("cryptogramme"))).get();
					clientToSave.setCarteBancaire(cbAlreadyBind);
					clientService.createClient(clientToSave);
				}
		
				reservationToSave.setClient(clientToSave);
				reservationToSave.setChambre(chambreService
						.findChambreById(Integer.parseInt(reservation.getFirst("identifiantOffre"))).get());
				reservationService.createReservation(reservationToSave);

				response = reservationToSave.toString();

			} else {

				response = "Erreur l'offre est déjà réservée pour les dates demandées";
			}
			return response;
		}
	
	@PostMapping(uri+"/test")
	public String servation(
			@RequestBody String numeroCarte, 
			@RequestBody String cryptogramme, 
			@RequestBody String cardDateExpiration) 
	{
		System.out.println("Bonjour");
		int crypto = Integer.parseInt(cryptogramme);
		CarteBancaire cb = new CarteBancaire(numeroCarte, cardDateExpiration, crypto);
		System.out.println(cb.toString());
		carteBancaireService.createCarteBancaire(cb);
		
		return "ok";

	}		
}



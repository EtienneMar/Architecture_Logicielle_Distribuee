package web.service.services;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;


import web.service.model.CarteBancaire;
import web.service.model.Chambre;
import web.service.model.Client;
import web.service.model.Etoiles;
import web.service.model.Hotel;
import web.service.model.Offre;
import web.service.model.Reservation;
import web.service.model.Ville;
import web.service.model.infoAgence;

public class HotelRepository  {

    private Hotel hotelsToBind;

//	private static final String PathForChamberPicture = "./src/main/java/Image_Chambre/";
	protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	protected static boolean isAvailable;

    public HotelRepository(Hotel hotel) {
        this.hotelsToBind = hotel;
        
		//Test instanciation des Chambres resultat attendu identifiant 1,2,3 et non 1,2,3 ... 4,5,6
		for (Chambre chambre : hotelsToBind.getListChambres()) {System.out.println(chambre.getIdentifiant());}
    }    
    
    //Methode XML
    public String getNomHotel() {
		return hotelsToBind.getNom();
	}
	
	public String getAddressHotel() {
		return hotelsToBind.getAdresse().toString();
	}
	
	public String getPays() {
		return hotelsToBind.getAdresse().getPays();
	}
		
	public ArrayList<Integer> getIdentifiantOffre() {
		ArrayList<Integer> listIdentifiantOffre = new ArrayList<>();
		for (Chambre chambre : hotelsToBind.getListChambres()) {
			listIdentifiantOffre.add(chambre.getIdentifiant());
		}
		return listIdentifiantOffre;
	}
	
	public ArrayList<Offre> consultationDisponiblite(int identifiantAgence, String loginAgence, String dateDebut, String dateFin, int nbPersonne, String pays, Ville ville, Etoiles etoiles, float prixMin, float prixMax) {

		
		Reservation reservation = new Reservation(dateDebut, dateFin, nbPersonne);
		System.out.println("dateArriveee"+dateDebut+"dateDepart"+dateFin);
		ArrayList<Offre> OffreDeChambreDispo  = new ArrayList<>();
		
		if (hotelsToBind.getAdresse().getPays().equals(pays) && hotelsToBind.getAdresse().getVille().equals(ville) && hotelsToBind.getEtoiles().equals(etoiles)) {
			
			float coeffPromotion = 1;
			//Test de la réduction
			Optional<infoAgence> filteredAgence = hotelsToBind.getListAgencesPartenaire().stream()
					.filter(agence -> agence.getIdentifiantAgence()== identifiantAgence 
							&& agence.getPassword().equals(loginAgence))
					.findFirst();
			System.out.println(filteredAgence.get().getPassword());
			if (filteredAgence.isPresent()) {
				System.out.printf("Super une réduction de %.2f pourcent ! \n", ((1-filteredAgence.get().getCoeffPromotion())*100));
				coeffPromotion = filteredAgence.get().getCoeffPromotion();
			}else {
				System.out.println("Pas de promotion !, on ne connait pas cette agence");
				
			}
			
			for (Chambre chambre : hotelsToBind.getListChambres()) {
				System.out.println(chambre.getNumero());
				if (chambre.getNbLits() >= nbPersonne && (prixMin <= chambre.getPrixChambre()*coeffPromotion && chambre.getPrixChambre() <= prixMax * coeffPromotion )) {
					if (chambre.getListReservationsChambre().isEmpty()) {
						System.out.println(chambre + "add directement car vide" );
						Offre offreEmpty = new Offre(filteredAgence.get().getCoeffPromotion(), chambre);
						OffreDeChambreDispo.add(offreEmpty);
					}else {
						for (Reservation reservationAlreadyBind : chambre.getListReservationsChambre()) {
							System.out.println(chambre + "test si pas vide");
							if ( ! (reservation.getDateDepart().isBefore(reservationAlreadyBind.getDateArrivee())
									|| reservationAlreadyBind.getDateDepart().isBefore(reservation.getDateArrivee()))){
								System.out.println(chambre.toString()+ " est prise au date suivante arrivée : "+reservation.getDateArrivee()+ " , départ "+reservation.getDateDepart());
								System.out.println(chambre.toString() + isAvailable);
							}else {
								System.err.println(chambre.toString()+" ne passe pas dans le test");
								Offre offreEmpty = new Offre(filteredAgence.get().getCoeffPromotion(), chambre); 
								OffreDeChambreDispo.add(offreEmpty);
							}
						}
					}
				}
			}
		}
		System.out.println(OffreDeChambreDispo.toString());
		return OffreDeChambreDispo;
	}

	public String doReservation(int identifiantAgence, String loginAgence, String passwordAgence, int idOffre, String prenomClient, String nomClient, String numeroCarte, 
			int cryptogramme, String cardDateExpiration , String dateDebut, String dateFin, int nbLits) {
		
		Reservation reservation = new Reservation(dateDebut, dateFin, nbLits);
		String response =  "Erreur il n'y a aucune offre qui correspond avec l'identifiant de réservation renseigner. \n"; 
		String testAgence =  ""; 
		
		Optional<infoAgence> filteredAgence = hotelsToBind.getListAgencesPartenaire().stream()
				.filter(agence -> agence.getIdentifiantAgence()== identifiantAgence 
						&& agence.getLogin().equals(loginAgence)
						&& agence.getPassword().equals(passwordAgence))
				.findFirst();
		if (filteredAgence.isPresent()) {
			System.out.println(filteredAgence.get().toString()+" a bien été identifiée");
			testAgence = String.format("Super vous réservez depuis une agence partenaire vous avez une réduction de %.2f pourcent ! \n", 
					((1-filteredAgence.get().getCoeffPromotion())*100));
		}else {
			testAgence = "Vous ne réservez pas depuis une agence partenaire ! \n";
			System.out.println("Vous ne réservez pas depuis une agence partenaire ! \n");
		}
	

		
		for (Chambre chambre : hotelsToBind.getListChambres()){
			if (chambre.getIdentifiant() == idOffre) {
				System.out.println(chambre.getIdentifiant()+"\n");
				if (! chambre.getListReservationsChambre().isEmpty()) {
					isAvailable = true;
					for (Reservation reservationAlreadyBind : chambre.getListReservationsChambre()) {
						System.out.println(reservationAlreadyBind);
						System.out.println(reservationAlreadyBind.toString());
						if ( ! (reservation.getDateDepart().isBefore(reservationAlreadyBind.getDateArrivee())
								|| reservationAlreadyBind.getDateDepart().isBefore(reservation.getDateArrivee()))) {
							isAvailable = false;
						}
					}
					if (isAvailable) {
						addReservationChambre(chambre, reservation, numeroCarte, cardDateExpiration, cryptogramme, nomClient, prenomClient);
						response = "Merci d'avoir réservé la chambre n°"+chambre.getNumero()+" pour les dates suivantes : "+reservation.getDateArrivee().format(DATE_TIME_FORMATTER)+" - "
								+reservation.getDateDepart().format(DATE_TIME_FORMATTER)+ " elle correspond à l'identifiant d'offre "+chambre.getIdentifiant()+"\n";
						System.out.println("isAvailable test boucle \n");
						}
				}else {
					addReservationChambre(chambre, reservation, numeroCarte, cardDateExpiration, cryptogramme, nomClient, prenomClient);
					response = "Merci d'avoir réservé la chambre n°"+chambre.getNumero()+" pour les dates suivantes : "+reservation.getDateArrivee().format(DATE_TIME_FORMATTER)+" - "
								+reservation.getDateDepart().format(DATE_TIME_FORMATTER)+ " elle correspond à l'identifiant d'offre "+chambre.getIdentifiant()+"\n";
					System.out.println("else state if chambrelist n'est pas vide \n");
					break;
				}
			}
		}
		response += testAgence;
		return response;
	}

	private static void addReservationChambre (Chambre chambre, Reservation reservation, String numeroCarte, String carteDateExpiration, 
												int cryptogramme, String nomClient, String prenomClient) 
	{
		CarteBancaire cb = new CarteBancaire(numeroCarte, carteDateExpiration, cryptogramme);
		Client client = new Client(nomClient, prenomClient, cb);
		reservation.setClient(client);
		chambre.getListReservationsChambre().add(reservation);
		System.out.println("j'ai fait la "+reservation);
	}
	
}
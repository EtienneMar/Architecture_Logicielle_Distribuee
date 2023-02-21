package agence.rest.server.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import agence.rest.server.model.Agence;
import agence.rest.server.model.Offre;
import agence.rest.server.services.AgenceService;
import agence.rest.server.services.HotelWebServiceURLService;

@RestController
public class AgenceController {

	@Autowired
	private AgenceService agenceService;
	
	@Autowired
	private HotelWebServiceURLService hotelWebServiceURLService;
	
	@Autowired
	private RestTemplate proxy;
	
	private static final String uri = "agence/api";
	
	@GetMapping(uri+"/ville")
	public ArrayList<String> AgenceVille() {
		ArrayList<String> villeAgence = new ArrayList<>();
		
		List<String> listHotelWebServiceURL = new ArrayList<>();
		listHotelWebServiceURL = hotelWebServiceURLService.getAllURL();
		
		for (String hotelWebServiceURL : listHotelWebServiceURL) {
			String uriNomVille = hotelWebServiceURL+"nomville";
			
			String VilleAddToList = proxy.getForObject(uriNomVille, String.class);
			if (! villeAgence.contains(VilleAddToList)) {
				villeAgence.add(VilleAddToList); 
			}
		}
		
		return villeAgence;
		
	}


	@GetMapping(uri+"/offre/ville={ville}/date_arrivee={dateArrivee}/date_depart={dateDepart}"
			+ "/nbPersonne={nbPersonne}/etoiles={etoiles}")
	public ArrayList<Offre> consultationOffre(@PathVariable int nbPersonne, @PathVariable String dateArrivee, @PathVariable String dateDepart, 
			 @PathVariable String ville, @PathVariable int etoiles) throws IOException{
	
		//Je récupère mon agence
		Optional<Agence> optAgence = agenceService.getAgence(); 
		Agence agence = optAgence.get();
		ArrayList<Offre> listOffreDispo = new ArrayList<>();
		
		List<String> listHotelWebServiceURL = new ArrayList<>();
		listHotelWebServiceURL = hotelWebServiceURLService.getAllURL();
		
		for (String hotelWebServiceURL : listHotelWebServiceURL) {
			String uri = hotelWebServiceURL+"consultation/id_agence="+agence.getIdentifiant()+"/password_agence="+agence.getPassword()+"/date_arrivee="+dateArrivee
	    			+"/date_depart="+dateDepart+"/nbPersonne="+nbPersonne+"/ville="+ville+"/etoiles="+etoiles;
    	
			Offre [] listOffre = proxy.getForObject(uri, Offre[].class);
			//J'ajoute le résultat à l'arraylist
		
			System.out.println("test");
			System.out.println(listOffre.toString());
			
			if (Arrays.asList(listOffre).isEmpty()) { 
				System.out.println("Pour "+ hotelWebServiceURL+"nous n'avons rien trouvé");
				System.out.println();
			}else {
				Arrays.asList(listOffre).forEach(System.out::println);
				for (Offre offre : Arrays.asList(listOffre)) {
					System.out.println(offre);
					offre.setNomAgence(agence.getNom());
					listOffreDispo.add(offre);
				}
			}
			//Pour chaque résultat je rajoute les informations manquantes.
		}
		return listOffreDispo;
	}
	
}


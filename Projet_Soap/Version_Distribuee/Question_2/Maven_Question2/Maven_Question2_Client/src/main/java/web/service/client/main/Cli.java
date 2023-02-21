package web.service.client.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import org.glassfish.pfl.basic.contain.Pair;

import client.cli.input.processor.*;
import web.service.client.consultation.wsimport.Etoiles;
import web.service.client.consultation.wsimport.IWebService1Consultation;
import web.service.client.consultation.wsimport.Offre;
import web.service.client.consultation.wsimport.Ville;
import web.service.client.consultation.wsimport.WebService1ConsultationImplService;
import web.service.client.reservation.wsimport.IWebService2Reservation;
import web.service.client.reservation.wsimport.WebService2ReservationImplService;


public class Cli extends CliAbstractMain{
	
	public static IntegerInputProcessor integerInputProcessor;  
	public static StringAlphaNumericOnlyInputProcessor stringAlphaNumericOnlyInputProcessor;
	public static StringAlphabetOnlyInputProcessor stringAlphabetOnlyInputProcessor;
	public static StringOnlySpaceExceptionInputProcessor stringOnlySpaceExceptionInputProcessor;
	public static DateInputProcessorFirstDateArrivee dateInputProcessor; 
	public static VilleInputProcessor villeInputProcessor;
	public static FloatPriceInputProcessorFirstPriceMin prixIntervalleInputProcessor;
	public static CardExpirationInputProcessor carteExpirationInputProcessor;
	
	public static LinkedHashMap<String, String> KeyURLWebService1_ValueURLWebService2 = new LinkedHashMap<>();
	
	public static int identifiantAgenceTest;
	public static Agence selectedAgenceToProxy = null;
	public static boolean consultation = false;
	public static String passwordAgenceToProxy = null;
	public static int nbPersonToProxy = -1;
	public static ArrayList<String> listePays = new ArrayList<>();
	public static String dateArriveeToProxy = null;
	public static String dateDepartToProxy = null;
	public static HashMap<String, List<Integer>> KeyNomHotel_ValueIdOffre = new HashMap<>(); 
	

	public static ArrayList<Agence> listAgence = new ArrayList<>(Arrays.asList(
			new Agence("Selectour",1, "sel_log", "selectour"),
			new Agence("TripAvis", 2, "trip_log", "tripavis"),
			new Agence("ViaHotel", 3, "via_log", "viahotel")));
	

	public static void main (String[] args) {

		initHashMapWebServiceURL();
		Cli main = new Cli();
		Scanner input = new Scanner(System.in);
		String userInput ="";
		IWebService1Consultation proxyConsultation = null;
		IWebService2Reservation proxyReservation = null;		
		try {
			do {
				main.menu();
				userInput = input.nextLine();
				main.processUserInput(input, userInput, proxyConsultation, proxyReservation);
				Thread.sleep(2000);
			}while(! userInput.equals(QUIT)); 
		} catch (Exception   e) {
			System.err.println("Erreur l'URL WSDL renseigner n'est pas valide");
		}

	}
	
	@Override
	protected void menu() {
		System.out.println("0 : Fermer la session client");
		System.out.println("1 : Consulter les offres disponibles");
		System.out.println("2 : Réserver une offre donnée");
		
	}
		
	private void processUserInput(Scanner input, String userInput, IWebService1Consultation proxyConsultation, IWebService2Reservation proxyReservation) {
		try {
			switch (userInput) {
			case "1":
				
				agenceIndentifiantInputTest(input);

				agencePasswordInputTest(input);

				dateInputProcessor = new DateInputProcessorFirstDateArrivee(input, 
						"Veuillez entrer une date d'arrivée (jj-mm-yyyy) après le : ");
				ArrayList<String> dateConsultationStockInput = new ArrayList<>();
				dateConsultationStockInput.addAll(dateInputProcessor.date());
				dateArriveeToProxy = dateConsultationStockInput.get(0);
				dateDepartToProxy = dateConsultationStockInput.get(1);
				
				nbPersonInputTest(input);
				
				for (String urlWebService1Key : KeyURLWebService1_ValueURLWebService2.keySet()) {
					proxyConsultation = getWebService1ConsultationProxy(urlWebService1Key);
					if (! listePays.contains(proxyConsultation.getPays())) {
						listePays.add(proxyConsultation.getPays());
					}
				}
				System.out.println(listePays);
				
				stringAlphabetOnlyInputProcessor = new StringAlphabetOnlyInputProcessor(input, 
						"Veuillez entrez un pays figurant dans la liste des pays proposés :\n"+listePays);
				String paysToProxy = "";
				do {
					paysToProxy = stringAlphabetOnlyInputProcessor.stringProcess();
				} while (! listePays.contains(paysToProxy));
				
				villeInputProcessor = new VilleInputProcessor(input, AfficheAllVilles());
				Ville villeToProxy = Ville.valueOf(villeInputProcessor.villeprocess());
				
				integerInputProcessor = new IntegerInputProcessor(input, 
						"Quelle catégorie d'hotel recherchez-vous (nombres d'étoiles) : 1, 2, 3, 4 ou 5 étoiles ?");
				int etoilesSelected = 0;
				do {
					etoilesSelected = integerInputProcessor.integerProcess();
					if (etoilesSelected <= 0 || etoilesSelected >=6) {
						System.err.println("Le nombre d'étoiles d'un hotel ne peut être compris qu'entre 1 et 5 !");
					}
				} while (etoilesSelected <= 0 || etoilesSelected >=6);
				Etoiles etoilesToProxy = Etoiles.values()[etoilesSelected - 1];
				
				//FloatPriceInputProcessorFirstPriceMin result for prixMin then for prixMax
				prixIntervalleInputProcessor = new FloatPriceInputProcessorFirstPriceMin(input, 
						"Veuillez indiquer le prix minimum différent de 0 pour une intervalle de prix :");
				ArrayList<Float> prixIntervalleStockInput = new ArrayList<>();
				prixIntervalleStockInput.addAll(prixIntervalleInputProcessor.prixIntervalle());
				float prixMinToProxy = prixIntervalleStockInput.get(0);
				float prixMaxToProxy = prixIntervalleStockInput.get(1);
								
				List<Offre> consultation = new ArrayList<>();
				String toPrint ="";
				for (String urlWebService1Key : KeyURLWebService1_ValueURLWebService2.keySet()) {
					System.out.println(urlWebService1Key);
					proxyConsultation = getWebService1ConsultationProxy(urlWebService1Key);
					System.out.println(listAgence.get(0).getIdentifiantAgence() + listAgence.get(0).getPassword());
					consultation.addAll(proxyConsultation.consultationDisponiblite(selectedAgenceToProxy.getIdentifiantAgence(), passwordAgenceToProxy, dateArriveeToProxy, dateDepartToProxy, 
							nbPersonToProxy, "France", villeToProxy, etoilesToProxy, prixMinToProxy, prixMaxToProxy));
					
					if (consultation.isEmpty()) {
						System.err.println("Erreur nous n'avons pas trouvé d'offre correspondant à votre demande à l'hotel "+proxyConsultation.getHotelNom());
					} 
					else {
						for (Offre offre : consultation) {
							toPrint = (offre.getHotelNom()+" Chambre n°"+offre.getNumero()+"\n"
									+"Prix Chambre : "+offre.getPrixChambreWithReduction()+" €\n"
									+"Identifiant de l'offre : "+offre.getIdentifiant()+"\n"
									+"disponibilité : \n") ;
							for (int i = 0; i < offre.getDisponibilitee().size(); i++){
								toPrint += offre.getDisponibilitee().get(i)+" - ";
								toPrint += offre.getDisponibilitee().get(i+=1)+"\n";
							}
							System.out.println(toPrint);
						}
					
					}
				}
				try {
					GuiDiaporama pictureChambre = new GuiDiaporama(consultation);
					pictureChambre.getFrame().setVisible(true);
					pictureChambre.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
				}
				
			
				break;
				
			case"2": 
				
				agenceIndentifiantInputTest(input);
				
				stringOnlySpaceExceptionInputProcessor = new StringOnlySpaceExceptionInputProcessor(input, "Merci d'entrer le login de l'agence");
				String loginAgenceTest = null;
				do {
					loginAgenceTest = stringOnlySpaceExceptionInputProcessor.stringProcess();
				} while (! loginAgenceTest.equals(selectedAgenceToProxy.getLogin()));
				
				agencePasswordInputTest(input);
			
//				//Testing identifiantOffre
				String urlSelected = null;
				boolean test = false;
				integerInputProcessor = new IntegerInputProcessor(input,
						"Merci d'entrer un identifiant de l'offre que vous avez sélectionné s'il vous plaît"); 
				int identifiantOffreToProxy = -1;
				for (String urlWebService2Value : KeyURLWebService1_ValueURLWebService2.values()) {
					System.out.println(urlWebService2Value);
					proxyReservation = getWebService2ReservationProxy(urlWebService2Value);
					System.out.println("Hotel : "+proxyReservation.getNomHotel());
					System.out.println(proxyReservation.listIdentifiantOffre().toString());
					KeyNomHotel_ValueIdOffre.put(urlWebService2Value, proxyReservation.listIdentifiantOffre());
					System.out.println(KeyNomHotel_ValueIdOffre.values());
				}
				do {
					identifiantOffreToProxy = integerInputProcessor.integerProcess();
					for (List<Integer> value : KeyNomHotel_ValueIdOffre.values()) {
						for (int i : value) {
							if (value.contains(i)) {
								test = true;
								break;
							}
						}
						urlSelected = getKeyByValue(KeyNomHotel_ValueIdOffre, value);
						System.out.println("Hotel Choisi" +urlSelected);
						break;
					}
				} while (test == false);

				//Testing prenomClient
				stringAlphabetOnlyInputProcessor = new StringAlphabetOnlyInputProcessor(input, 
						"Merci d'entrer votre prénom");
				String prenomClientToProxy = stringAlphabetOnlyInputProcessor.stringProcess();
			
				
				//Testing nomClient
				stringAlphabetOnlyInputProcessor = new StringAlphabetOnlyInputProcessor(input, "Merci d'entrer votre nom");
				String nomClientToProxy = stringAlphabetOnlyInputProcessor.stringProcess();
				
				//Testing numeroCarte,
				stringAlphaNumericOnlyInputProcessor = new StringAlphaNumericOnlyInputProcessor(input, 
						"Veuillez entrer votre numéro à 16 chiffre de Carte Bancaire");
				String numeroCarteBancaireToProxy = null;
				do {
					numeroCarteBancaireToProxy = stringAlphaNumericOnlyInputProcessor.stringProcess();
					if (numeroCarteBancaireToProxy.length() != 16) {
						System.err.println("Un numéro de Carte Bancaire doit être composé de 16 chiffre,"
								+ "merci de réessayer votre entrée");
					}
				} while (numeroCarteBancaireToProxy.length() != 16);
				
				//Testing dateExpirationCarte
				carteExpirationInputProcessor = new CardExpirationInputProcessor(input, 
						"Merci d'entrer la date d'expiration au format (mm-yy) de votre carte ");
				String carteExpirationToProxy = carteExpirationInputProcessor.stringProcess();
			 
				
				//Testing carteCryptogramme,
				integerInputProcessor = new IntegerInputProcessor(input, 
						"Merci de bien vouloir entrer le cryptogramme de votre carte Bancaire ");
				int carteCryptogrammeToProxy = -1;
				do {
					carteCryptogrammeToProxy = integerInputProcessor.integerProcess();
					if (carteCryptogrammeToProxy <0 || carteCryptogrammeToProxy > 999){
						System.err.println("Un cryptogramme ne peut être compris qu'entre 0"
								+ "et 999, veuillez réessayer");
					}
				} while (carteCryptogrammeToProxy < 0 || carteCryptogrammeToProxy > 999);
				
				dateInputProcessor = new DateInputProcessorFirstDateArrivee(input, 
						"Veuillez entrer une date d'arrivée (jj-mm-yyyy) après le : ");
				ArrayList<String> dateReservationStockInput = new ArrayList<>();
				dateReservationStockInput.addAll(dateInputProcessor.date());
				dateArriveeToProxy = dateReservationStockInput.get(0);
				dateDepartToProxy = dateReservationStockInput.get(1);
		
				nbPersonInputTest(input);
				
				proxyReservation = getWebService2ReservationProxy(urlSelected);
				System.out.println(proxyReservation.doReservation(selectedAgenceToProxy.getIdentifiantAgence(), loginAgenceTest, passwordAgenceToProxy, 
						identifiantOffreToProxy, prenomClientToProxy, 
						nomClientToProxy, numeroCarteBancaireToProxy, carteCryptogrammeToProxy, carteExpirationToProxy, 
						dateArriveeToProxy, dateDepartToProxy, nbPersonToProxy
						)); 
				break;
				
			case QUIT:
				System.out.println("Le client se déconnecte, à une prochaine fois... ! ");
				return;
			default:
				System.err.println("Le nombre entrée ne fait pas partie des choiux possibles"
						+"merci d'entrée le nombre 0, 1 ou 2.");
				return;
			}
		} catch (Exception e) {
		}
	}	

	/* METHODES D'INITIALISATION DES URLS AFIN DE SE CONNECTER AU DIFFERENTS SERVICES WEBS*/
	private static void initHashMapWebServiceURL () {
		KeyURLWebService1_ValueURLWebService2.put("http://localhost:8888/HotelWebService1Consultation", "http://localhost:8888/HotelWebService2Reservation");
		KeyURLWebService1_ValueURLWebService2.put("http://localhost:4444/HotelWebService1Consultation", "http://localhost:4444/HotelWebService2Reservation");
	}
	
	private static IWebService1Consultation getWebService1ConsultationProxy(String urlWebService1) throws MalformedURLException {
		return new WebService1ConsultationImplService(new URL(urlWebService1)).getWebService1ConsultationImplPort();
	}
	
	private static IWebService2Reservation getWebService2ReservationProxy(String urlWebService2) throws MalformedURLException {
		return new WebService2ReservationImplService(new URL(urlWebService2)).getWebService2ReservationImplPort();
	}
	
	/* METHODES REPETITIVES CLI SCANNER*/
	private static  String AfficheAllVilles () {
		String AllVilles = "Sélection une ville parmi la liste des Villes disponibles : \n";
		for (Ville ville : Ville.values()) {
			AllVilles += (ville.value().substring(0, 1)+ville.value().substring(1).toLowerCase()+", ");
		}
		return AllVilles;
	}
	
	private static String allIdentifiantAgence () {
		String allidentifiant = "liste identifiant : ";
		for (Agence agence : listAgence) {
			allidentifiant += String.format("%x, ", agence.getIdentifiantAgence()); 
		}
		return allidentifiant;
	}
	
	public static String getKeyByValue(HashMap<String,List<Integer>> keyNomHotel_ValueIdOffre2, List<Integer> value) {
		String toReturn = null;
	    for (Entry<String, List<Integer>> entry : keyNomHotel_ValueIdOffre2.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            toReturn = entry.getKey();
	            
	        }
	    }
	    return toReturn;
	}


	private static void agenceIndentifiantInputTest (Scanner input) throws IOException {
		integerInputProcessor = new IntegerInputProcessor(input, 
				"Merci d'entrée un identifiant correspondant aux identifiants d'agence suivants :\n"
						+allIdentifiantAgence() );
		do {
			identifiantAgenceTest = integerInputProcessor.integerProcess();
			for (Agence agence : listAgence) {
				if (agence.getIdentifiantAgence() == identifiantAgenceTest) {
					selectedAgenceToProxy = agence;
					consultation = true;
					break;
				}
			}
			if (! consultation) {
				System.err.println("Cet identifiant d'agence n'existe pas merci de bien vouloir en sélectionner un parmi ceux proposés");
			}
		} while (consultation == false);	
	}
	
	private static String agencePasswordInputTest(Scanner input) throws IOException {
		stringOnlySpaceExceptionInputProcessor = new StringOnlySpaceExceptionInputProcessor(input, 
				"Merci d'entrée le bon mot de passe de l'Agence : "+selectedAgenceToProxy.getNomAgence());
		do {
			passwordAgenceToProxy = stringOnlySpaceExceptionInputProcessor.stringProcess();
			if (! passwordAgenceToProxy.equals(selectedAgenceToProxy.getPassword())){
				System.err.println("Le mot de passe que vous avez rentrée n'est pas valide pour l'agence"
						+" veuillez réessayer");
			}
		} while (! passwordAgenceToProxy.equals(selectedAgenceToProxy.getPassword()));
		return passwordAgenceToProxy;
	}
	
	private static void nbPersonInputTest(Scanner input) throws IOException {
		integerInputProcessor = new IntegerInputProcessor(input, 
				"Merci d'entrée un nombre de personne qui doit être supérieur à 0"); 
		do {
			nbPersonToProxy = integerInputProcessor.integerProcess();
		}while (nbPersonToProxy <= 0); 
		
	}
	

}
	

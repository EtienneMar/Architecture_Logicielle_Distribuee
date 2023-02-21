package hotel.rest.server;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import hotel.rest.server.model.AgencePartenaire;
import hotel.rest.server.model.AgencePartenairePKID;
import hotel.rest.server.model.CarteBancaire;
import hotel.rest.server.model.Chambre;
import hotel.rest.server.model.Client;
import hotel.rest.server.model.Hotel;
import hotel.rest.server.model.Reservation;
import hotel.rest.server.repository.AgencePartenaireRepository;
import hotel.rest.server.repository.CarteBancaireRepository;
import hotel.rest.server.repository.ChambreRepository;
import hotel.rest.server.repository.HotelRepository;
import hotel.rest.server.repository.ReservationRepository;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EntityScan(basePackages = {"hotel.rest.server.model"})
public class Application implements CommandLineRunner{
	
	
	// Repository
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private AgencePartenaireRepository agencePartenaireRepository;
	@Autowired
	private CarteBancaireRepository carteBancaireRepository;
	@Autowired
	private ChambreRepository chambreRepository; 
	@Autowired
	private ReservationRepository reservationRepository; 

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
	
		Hotel hotel = new Hotel("La Verrerie", 3, "France", "Gaillac", 1, "Rue de l'Égalité", (float)43.9018, (float)1.89651);
		hotelRepository.save(hotel);
				
		AgencePartenairePKID agenceSelectourPKID = new AgencePartenairePKID();
		agenceSelectourPKID.setLoginAgence("sel_log");
		agenceSelectourPKID.setPasswordAgence("selectour");
		AgencePartenaire agenceSelectour = new AgencePartenaire(1,agenceSelectourPKID, (float) 0.9, hotel);
		
		AgencePartenairePKID agenceTripDreamPKID = new AgencePartenairePKID();
		agenceTripDreamPKID.setLoginAgence("trip_log");
		agenceTripDreamPKID.setPasswordAgence("tripDream");
		AgencePartenaire agenceagenceTripDream = new AgencePartenaire(22, agenceTripDreamPKID, (float) 0.9, hotel);
		
		agencePartenaireRepository.save(agenceSelectour);
		agencePartenaireRepository.save(agenceagenceTripDream);
		
		CarteBancaire cb = new CarteBancaire("1234567891234567", "02/23", 555); 
		CarteBancaire cb1 = new CarteBancaire("1234567891234567", "02/25", 444); 		
		cb.setIdCarteBancaire(1);
		cb1.setIdCarteBancaire(2);
		Client client = new Client("Creig", "Daniel", cb);
		Client client1 = new Client("Creig", "Victor", cb1);
		client.setId_client(1);
		client1.setId_client(2); 
		cb.setClient(client);
		cb1.setClient(client1);
		carteBancaireRepository.save(cb); 
		carteBancaireRepository.save(cb1);
		
		
		Chambre chambre10 = new Chambre(1, 10, 2, 70,"./src/main/java/Image_Chambre/pic1.jpg", hotel);

		Chambre chambre20 = new Chambre(2, 20, 3, 90,"./src/main/java/Image_Chambre/pic2.jpg", hotel);
	
		
		Chambre chambre30 = new Chambre(3, 30, 2, 85,"./src/main/java/Image_Chambre/pic3.jpg", hotel);
		
		chambreRepository.save(chambre10);
		chambreRepository.save(chambre20);
		chambreRepository.save(chambre30);
		
		
		Reservation reservation1 = new Reservation(LocalDate.now(), LocalDate.now().plusDays(1), 2);	
		reservation1.setClient(client1);
		reservation1.setChambre(chambre10);
		reservationRepository.save(reservation1);
		
	}

}

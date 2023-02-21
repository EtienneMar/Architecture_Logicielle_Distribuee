package agence.rest.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import agence.rest.server.model.Agence;
import agence.rest.server.model.HotelWebServiceURL;
import agence.rest.server.repository.AgenceRepository;
import agence.rest.server.repository.HotelWebServiceURLRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	// Repository
	@Autowired
	AgenceRepository agenceRepository;
	
	@Autowired
	HotelWebServiceURLRepository hotelWebServiceURLRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Agence agence = new Agence(1, "TripDream", "trip_log", "tripDream");
		agenceRepository.save(agence);
		
		HotelWebServiceURL laVerrerieWebServiceURL = new HotelWebServiceURL("LaVerrerie", "http://localhost:8080/hotelservice/api/hotel/");
		HotelWebServiceURL progressWebServiceURL = new HotelWebServiceURL("Progress", "http://localhost:5555/hotelservice/api/hotel/");
		hotelWebServiceURLRepository.save(laVerrerieWebServiceURL);
		hotelWebServiceURLRepository.save(progressWebServiceURL);
		
	}
	

}

package hotel.rest.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.rest.server.model.Hotel;
import hotel.rest.server.repository.HotelRepository;

@Service
public class HotelService {
	
	@Autowired
	private HotelRepository hotelRepository; 
	
	public Iterable<Hotel> getHotel() {
		return hotelRepository.findAll();
	}
	
	public Optional<Hotel> getHotelByPaysAndVilleAndEtoiles(String pays, String ville, int etoiles){
		return hotelRepository.findByPaysAndVilleAndEtoiles(pays, ville, etoiles);
	}
	
	public Optional<Hotel> getHotelById (String nom){
		return hotelRepository.findById(nom);
	}
	
	public String getToStringHotel(){
		Optional<Hotel> getHotel = hotelRepository.findById("La Verrerie");
		Hotel laVerrerie = getHotel.get();
		return laVerrerie.toString();
	}

}

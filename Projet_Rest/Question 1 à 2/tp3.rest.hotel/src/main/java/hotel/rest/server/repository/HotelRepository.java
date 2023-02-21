package hotel.rest.server.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hotel.rest.server.model.Hotel;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, String>{

	//requête dérivée pour tester si l'hotel existe
	public Optional<Hotel> findByPaysAndVilleAndEtoiles (String pays, String ville, int etoiles); 

}

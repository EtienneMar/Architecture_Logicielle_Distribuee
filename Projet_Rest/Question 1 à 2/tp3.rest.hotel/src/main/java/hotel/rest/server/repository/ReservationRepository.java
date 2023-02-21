package hotel.rest.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hotel.rest.server.model.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer>{
	
	@Query(value= "SELECT * FROM reservation WHERE date_depart > :dateArrivee AND date_arrivee < :dateDepart", nativeQuery=true)
	public List<Reservation> findIfReservationIsPossible (@Param("dateArrivee") String dateArrivee, @Param("dateDepart") String dateDepart); 
	
}

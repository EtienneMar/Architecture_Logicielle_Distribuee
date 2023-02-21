package hotel.rest.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.rest.server.model.Reservation;
import hotel.rest.server.repository.ReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository; 
	
	public Iterable<Reservation> getReservation() {
		return reservationRepository.findAll();
	}
	
	public Optional<Reservation> getClientById(Integer i){
		return reservationRepository.findById(i);
	}

	public Reservation createReservation (Reservation reservation) {
		return reservationRepository.save(reservation);
	}
	
	public List<Reservation> findIfReservationIsPossible (String dateArrivee, String dateDepart){
		return reservationRepository.findIfReservationIsPossible(dateArrivee, dateDepart); 
	}

}

package hotel.rest.server.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.rest.server.model.Chambre;
import hotel.rest.server.repository.ChambreRepository;

@Service
public class ChambreService {

	@Autowired
	private ChambreRepository chambreRepository; 

	public Iterable<Chambre> getChambre() {
		return chambreRepository.findAll();
	}

	public Optional<Chambre> findChambreById(int id){
		return chambreRepository.findChambreById(id);
	}
	
	public ArrayList<String> listIdentifiantOffre (){
		ArrayList<String> listOffre = new ArrayList<>();
		chambreRepository.findAll().forEach(chambre -> listOffre.add(Integer.toString(chambre.getIdChambre())));
		return listOffre;
	}
	
	public List<Chambre> findChambreByDisponbilitee (String dateArrivee, String dateDepart, float coeffPromotion, float prixMin, float prixMax, int nbPersonne){
		return chambreRepository.findChambreDisponibilitee(dateArrivee, dateDepart, coeffPromotion, prixMin, prixMax, nbPersonne);
	}
}

package hotel.rest.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.rest.server.repository.AgencePartenaireRepository;

@Service
public class AgencePartenaireService {

	@Autowired
	private AgencePartenaireRepository AgencePartenaireRepository; 
	
	
	public float findByLoginAndPasswordAndId(String loginAgence, String passwordAgence, int idAgence) {
		return AgencePartenaireRepository.findByLoginAgenceAndPassword_agenceAndId_Agence(loginAgence, passwordAgence, idAgence).get().getCoeffPromotion();
	}
	
}

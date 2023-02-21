package agence.rest.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agence.rest.server.model.Agence;
import agence.rest.server.repository.AgenceRepository;



@Service
public class AgenceService {

	@Autowired
	private AgenceRepository agenceRepository;
	
	public Optional<Agence> getAgence () {
		return agenceRepository.findById(1);
	}
	
}

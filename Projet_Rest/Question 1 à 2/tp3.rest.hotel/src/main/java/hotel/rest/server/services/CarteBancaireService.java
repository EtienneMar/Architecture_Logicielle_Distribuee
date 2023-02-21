package hotel.rest.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.rest.server.model.CarteBancaire;
import hotel.rest.server.repository.CarteBancaireRepository;

@Service
public class CarteBancaireService {
	

	@Autowired
	private CarteBancaireRepository carteBancaireRepositoryRepository; 
	
	public CarteBancaire createCarteBancaire (CarteBancaire cb) {
		return carteBancaireRepositoryRepository.save(cb);
	}
	
	public Optional<CarteBancaire> findCarteBancaireByNumeroANDcryptogrammeANDdateExpiration(String numeroCarte, String dateExpiration, int cryptogramme){
		return carteBancaireRepositoryRepository.findCarteBancaireByNumeroANDcryptogrammeANDdateExpiration(numeroCarte, dateExpiration, cryptogramme);
	}

}

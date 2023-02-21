package hotel.rest.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hotel.rest.server.model.CarteBancaire;

@Repository
public interface CarteBancaireRepository extends CrudRepository<CarteBancaire, Integer>{
	
	@Query(value = "SELECT * FROM cartebancaire WHERE numero_carte = :numeroCarte AND date_expiration= :dateExpiration AND cryptogramme = :cryptogramme", nativeQuery = true)
	public Optional<CarteBancaire> findCarteBancaireByNumeroANDcryptogrammeANDdateExpiration (String numeroCarte, String dateExpiration, int cryptogramme); 

}

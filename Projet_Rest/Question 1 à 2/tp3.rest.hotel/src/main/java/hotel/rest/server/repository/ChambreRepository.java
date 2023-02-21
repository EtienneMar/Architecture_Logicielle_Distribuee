package hotel.rest.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hotel.rest.server.model.Chambre;

@Repository
public interface ChambreRepository extends CrudRepository<Chambre, Integer>{

	@Query(value= "SELECT * FROM chambre WHERE id_chambre = :idChambre", nativeQuery=true)
	public Optional<Chambre> findChambreById(@Param("idChambre") int idChambre); 
	
	@Query(value = "SELECT * FROM chambre WHERE chambre.id_chambre NOT IN "
			+ "(SELECT reservation.id_chambre FROM reservation WHERE reservation.date_depart > :dateArrivee AND reservation.date_arrivee < :dateDepart)"
			+ "AND prix_chambre * :coeffPromotion BETWEEN :prixMin AND :prixMax AND nombre_lits >= :nbPersonne",
			nativeQuery = true)
	public List<Chambre> findChambreDisponibilitee(@Param("dateArrivee") String dateArrivee, @Param("dateDepart") String dateDepart
			, @Param("coeffPromotion") float coeffPromotion, @Param("prixMin") float prixMin, @Param("prixMax") float prixMax, 
			@Param("nbPersonne") int nbPersonne); 
}
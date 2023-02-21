package hotel.rest.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hotel.rest.server.model.AgencePartenaire;

@Repository
public interface AgencePartenaireRepository extends CrudRepository<AgencePartenaire, String>{

	//requête SQL NATIVE pour tester si l'agence existe
	@Query(value = "SELECT * FROM agence_partenaire WHERE login_agence = :loginAgence AND password_agence = :passwordAgence AND id_agence = :idAgence", nativeQuery = true)
	public Optional<AgencePartenaire> findByLoginAgenceAndPassword_agenceAndId_Agence(@Param("loginAgence") String loginAgence,@Param("passwordAgence") String passwordAgence, @Param("idAgence")int idAgence);
}

package agence.rest.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import agence.rest.server.model.Agence;


@Repository
public interface AgenceRepository extends CrudRepository<Agence, Integer>{


}

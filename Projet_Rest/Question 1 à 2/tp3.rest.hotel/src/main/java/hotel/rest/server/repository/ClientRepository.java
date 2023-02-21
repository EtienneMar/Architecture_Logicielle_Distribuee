package hotel.rest.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hotel.rest.server.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer>{

}

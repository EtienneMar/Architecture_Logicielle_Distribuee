package agence.rest.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import agence.rest.server.model.HotelWebServiceURL;


@Repository
public interface HotelWebServiceURLRepository extends CrudRepository<HotelWebServiceURL, String>{
	
	@Query(value = "SELECT web_service_url FROM hotel_web_service", nativeQuery = true)
	List<String> getAllURL ();

}

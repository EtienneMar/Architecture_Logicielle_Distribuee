package agence.rest.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agence.rest.server.repository.HotelWebServiceURLRepository;

@Service
public class HotelWebServiceURLService {
	
	@Autowired
	private HotelWebServiceURLRepository hotelWebServiceURLRepository;
	
	public List<String> getAllURL () {
		return hotelWebServiceURLRepository.getAllURL();
	}

}

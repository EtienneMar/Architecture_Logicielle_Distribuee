package hotel.rest.server.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hotel.rest.server.services.ChambreService;

@RestController
public class ChambreController {

	@Autowired
	ChambreService chambreService;
	
	private static final String uri = "hotelservice/api";
	
	@GetMapping(uri+"/hotel/chambre")
	public ArrayList<String> getAllIdentifiantOffre () {
		return chambreService.listIdentifiantOffre();
	}

}

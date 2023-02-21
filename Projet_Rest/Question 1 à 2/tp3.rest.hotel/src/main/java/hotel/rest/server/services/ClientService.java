package hotel.rest.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.rest.server.model.Client;
import hotel.rest.server.repository.ClientRepository;


@Service
public class ClientService {

		@Autowired
		private ClientRepository clientRepository; 
		
		public Iterable<Client> getClient() {
			return clientRepository.findAll();
		}
		
		public Optional<Client> getClientById(Integer i){
			return clientRepository.findById(i);
		}
		
		public Client createClient (Client client) {
			return clientRepository.save(client);
		}

}



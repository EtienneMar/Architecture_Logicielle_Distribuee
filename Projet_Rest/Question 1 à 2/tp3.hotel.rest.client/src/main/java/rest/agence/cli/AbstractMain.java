package rest.agence.cli;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public abstract class AbstractMain {
	public static String SERVICE_URL;
	public static final String QUIT = "0"; 
	
	protected void  SetTestServiceURL(BufferedReader inputReader) throws IOException {
		
		System.out.println("Mercie d'entrer un URL de service Web valide");
		SERVICE_URL = inputReader.readLine();
		
		while(!validServiceUrl()) {
			System.err.println("Error: "+SERVICE_URL+
			" n'est pas un URL de service web REST valide"
			+ "Merci d'essayer à nouveau ");
			SERVICE_URL = inputReader.readLine();
		}
	}
		
		protected abstract boolean validServiceUrl();
		
		protected abstract void menu(); 
}

	

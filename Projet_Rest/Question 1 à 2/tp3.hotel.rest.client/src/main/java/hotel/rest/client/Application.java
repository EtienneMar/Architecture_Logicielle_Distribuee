package hotel.rest.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"rest.agence.models", 
		"rest.agence.client",
		"rest.agence.cli"
		
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

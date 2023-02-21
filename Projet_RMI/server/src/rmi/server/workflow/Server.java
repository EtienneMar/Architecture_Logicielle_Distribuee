package rmi.server.workflow;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server {
	

	public Server() {}
		
	public static void main(String args[]) {
		
		//Ajout d'un système de sécurité
		System.setProperty("java.security.policy", "C:/Users/33683/Desktop/RMI_TP1/server/securityPolicy/security.policy");
		
		//System.setProperty("java.security.policy", "./securityPolicy/security.policy");
		
		//Ajout du code base
		SecurityManager securityManager = new SecurityManager();
		System.setSecurityManager(securityManager);
		System.setProperty("java.rmi.server.codebase", "file:/C:/Users/33683/Desktop/RMI_TP1/client/codebase/");
		
		//System.setProperty("java.rmi.server.codebase", "file:/RMI_TP1/client/codebase/");
		
		try {  	
			
			//Création du Registre
			Registry registry = LocateRegistry.createRegistry(1099);
			
			//Cration du client
			CabinetImpl cabinet = new CabinetImpl("CabinetVeterinaire");
			
			//Ajout d'un patient par défaut
			cabinet.fullAddPatient("Sammie", "Juliette", "Chien", 14, "Malinois", " en bonne santé"); 
			
			if (registry==null) {
				System.err.println("Répertoire introuvable");
			}else {
				//Enregistrement du client dans le registre
				registry.bind("CabinetVeterinaire", cabinet);
				System.err.println("Serveur prêt");
			}
		} catch (Exception e) {
			System.err.println("Server exception" + e.toString());
			e.printStackTrace();
		}
	}

}

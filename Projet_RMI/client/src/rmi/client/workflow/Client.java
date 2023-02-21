package rmi.client.workflow;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import rmi.commons.workflow.IAnimal;
import rmi.commons.workflow.ICabinet;
import rmi.commons.workflow.IClient;

public class Client {
	private Client () {}
	
	public static void main (String[] args) {
		
		String host = (args.length < 1) ? null : args[0];
		
		try {
			
			//Récupération du registre 
			Registry registry = LocateRegistry.getRegistry(1099);	
			
			//Stub pour récupérer le registre 
			ICabinet stub = (ICabinet) registry.lookup("CabinetVeterinaire");
			
			System.out.println("Entrée votre nom d'utilisateur SVP \n");
			IClient cabinet = new ClientImpl();
			Scanner scan = new Scanner(System.in);
			String saisieUsername = scan.next();
			cabinet.setUsername(saisieUsername);
			stub.addClient(cabinet);
			System.out.println();
			
			//Question 1 et 2 Récupération d'un patient dont seul le cabinet et distribué, récupération par référence 
			System.out.println("Voici l'animal par défaut prêt enregistrée dans le Cabinet");
			IAnimal Sammie =  stub.searchPatient("Sammie");
			System.out.println(Sammie.getInfoPatient());
			
			//Tableau de commande
			String saisieUser = ("default");
			while(!saisieUser.equals("0")) {
				
				System.out.println();
				System.out.println("Fait un choix d'entrée.");
				System.out.println("0 : Pour déconnecter le cabinet.");
				System.out.println("1 : Pour ajouter un animal.");
				System.out.println("2 : Pour supprimer un animal par son nom.");
				System.out.println("3 : Pour modifier le dossier d'un animal");
				System.out.println("4 : Pour afficher la liste des animaux actuellement pris en charge par le cabinet.");
				System.out.println("5 : Pour ajouter un animal d'espèce Chien et de moyenne de durée de vie de 12 ans qui n'est pas connu par le Serveur.");
				System.out.println("6 : Pour chercher précisément un animal que vous souhaitez.");
				System.out.println("7 : Pour simuler l'admission d'autant d'animaux que vous souhaitez.");
				System.out.println("8 : Pour Afficher le nom des cabinets actuellement connectés au serveur.");
				System.out.println("9 : Envoyer un message à tout les cabinets.");
				System.out.println();
	
				saisieUser = scan.next();
				
				//Déconnexion du client 
				
				if(saisieUser.equals("0")) {
					scan.close();
					System.out.println("Fermeture de la session");
					stub.eraseClient(cabinet);
					System.exit(0);
				}
	 
				//Question 3 Ajout d'un animal sans classe connu du client
				else if(saisieUser.equals("1")) {
					System.out.println("Ajouter le nom de l'animal");
					String saisieName = scan.next();
					System.out.println("Ajouter le prénom du Maître de l'animal");
					String saisieOwnerName = scan.next();
					System.out.println("Ajouter le nom de l'espèce de l'animal");
					String saisieSpecies = scan.next();
					System.out.println("Ajouter la durée de vie moyenne de l'espèce en question");
					int saisieAveragelife = scan.nextInt();
					System.out.println("Ajouter la race de l'animal");
					String saisieRace = scan.next();
					System.out.println("Ajouter l'état de santé de l'animal");
					String saisieDossier = scan.next();
					stub.fullAddPatient(saisieName, saisieOwnerName, saisieSpecies, saisieAveragelife, saisieRace, saisieDossier);	
				}
				
				//Solution hors question de suppression d'un animal pour tester l'alerte passage de seuil en baisse
				else if (saisieUser.equals("2")) {
					System.out.println("Entrée le nom de l'animal");
					String eraseAnimal = (scan.next());
					if (stub.eraseAnimal(eraseAnimal)==true) {
						System.out.println(eraseAnimal+" a été supprimé");
					}else {
						System.err.println(eraseAnimal+"n'existe pas dans la liste des patients il a été supprimé");
					}
				}
				
				//Question 1.2 Modification du dossier d'un animal
				else if (saisieUser.equals("3")) {
					System.out.println("Entrée le nom de l'animal.");
					String dossierToSet = scan.next();
					IAnimal animalToDossier = stub.searchPatient(dossierToSet);
					if (animalToDossier == null) {
						System.out.println("L'animal n'existe pas dans la liste des Patient.");
					}else {
						System.out.println("Entrée le nouvel état de l'animal.");
						animalToDossier.setDossier(scan.next());
						System.out.println(animalToDossier.getInfoPatient());
					}
				
				}
				
				//Solution hors question sémantique affichage de la liste des Patients 
				else if(saisieUser.equals("4")) {
					for (IAnimal animal : stub.getPatients()) {
						System.out.println("Voici la liste des patients pris en charge : "+animal.getInfoPatient());
					}
				}
				//Question 4 Ajout d'un animal connu du client par Codebase 
				else if(saisieUser.equals("5")) {
					System.out.println("Ajouter le nom de l'animal.");
					String saisieName = scan.next();
					System.out.println("Ajouter le prénom du Maître de l'animal.");
					String saisieOwnerName = scan.next();
					System.out.println("Ajouter la race de l'animal.");
					String saisieRace = scan.next();
					System.out.println("Ajouter l'état de santé de l'animal.");
					String saisieDossier = scan.next();
					stub.addPatient(saisieName, saisieOwnerName, new Dog(), saisieRace, saisieDossier);
				}
				//Question 2 Solution de Recherche d'un animal 
				else if(saisieUser.equals("6")){
					System.out.println("Entrée le nom de l'animal");
					String saisieToSearch = scan.next();
					IAnimal animalToSearch = stub.searchPatient(saisieToSearch);
					if (animalToSearch == null) {
						System.out.println("L'animal n'existe pas dans la liste des Patient.");
					}else {
					System.out.println("Voici l'animal recherché : "+animalToSearch.getInfoPatient());
					}
				}
				//Question 5 Solution hors question sémantique de test des alertes 
				else if(saisieUser.equals("7")){
					System.out.println("Entrée le nombre d'animaux à ajouter.");
					int saisie = scan.nextInt();
					for (int i=0; i<saisie; i++) {
						stub.fullAddPatient("Felix"+i, "Maître"+i, "Chat", 20, "Persan"+i, "en pleine forme.");
					}
				}
				//Question 5 Test d'enregistrement d'un client dans le serveur
				else if(saisieUser.equals("8")){
					for (IClient client : stub.getClients()) {
						System.out.println("Voici la liste des Cabinets connectés : "+client.getCabinetName());
					}
				}
				//Question 5 test d'un envoie de message aux différents clients
				else if(saisieUser.equals("9")){ 
					System.out.println("Entrée le message à envoyer à tous les clients connectés.");
					String printToAll = scan.next();
					printToAll += scan.nextLine();
					stub.alert("Le cabinet "+cabinet.getCabinetName()+" vous adresse le message suivant : \n"+printToAll);
				}
				
			}
		} catch (Exception e) {
			System.err.println("Client exception" + e.toString());
			e.printStackTrace();
		}
	}
}

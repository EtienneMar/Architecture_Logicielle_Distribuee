Etienne MARTIN-CHANTEREAU
Christophe HOUBRON

TP 2 SOAP WEBSERVICE

Bonjour Monsieur, afin que vous puissiez lire nos différents fichiers 
voila leurs contenus.

Le fichier Version_Non_Distribuee contient le code source pour les questions 
1 à 2.1.

Le fichier Version_Distribuee contient le code source pour les questions
2.2 à 3, et une partie de la 4 bonus.

Le PDF est contient notre note de conception et les schémas UML correspondant 
pour chacune des versions. 


Dezippez le fichier

Pour lancer la version non distribuée : QUESTION 1 A 2

Placez eclipse dans le répertoire TP_SOAP_CHRISTROPHE_HOUBRON_ETIENNE_MARTIN

Ouvrez eclipse

Cliquez sur file

Import

Une Fenetre s'ouvre cliquez sur General puis existing projects into WorkSpace

Browse


Selectionner le répertoire courant et décochais web.service.client et web.service

Importer le projet

Lancer MainApp pour lancer le projet

DATABASE EN DUR INFO :

Hotel ashotel = new Hotel("Ashotel",Etoiles.UNE);
		ashotel.setAdresse(120, "Avenue du Chat", Ville.Paris);
		Chambre chambre_as10 = new Chambre(10, 2, 60, ashotel);
		Chambre chambre_as20 = new Chambre(20, 2, 60, ashotel);
		Chambre chambre_as30 = new Chambre(30, 1, 60, ashotel);

Reservation : 

		Reservation resa1_dupond = new Reservation("10-11-2023", "11-11-2023", 2, dupond);
		Reservation resa1b_dupond = new Reservation("01-05-2023", "14-05-2023", 2, dupond);
		Reservation resa2_dupond = new Reservation("10-12-2023", "16-12-2023", 2, dupond);
		Reservation resa3_dupond = new Reservation("17-12-2023", "27-12-2023", 2, dupond);
		
		//Pour test ajout de resa1_dupond a l'hotel daysHotel chambre n°10
		addResaToChambre(chambre_day10, resa1_dupond);
		addResaToChambre(chambre_day10, resa1b_dupond);
		
		//Pour test ajout de resa2_dupond a l'hotel daysHotel chambre n°20
		addResaToChambre(chambre_day20, resa2_dupond);		
		
		//Pour test ajout de resa3_dupond a l'hotel daysHotel chambre n°30
		addResaToChambre(chambre_day30, resa3_dupond);
		
		Hotel daysHotel = new Hotel("DaysHotel",Etoiles.DEUX);
		daysHotel.setAdresse(10, "Route du Midi", Ville.Montpellier);
		Chambre chambre_day10 = new Chambre(10, 3, 90, daysHotel);
		Chambre chambre_day20 = new Chambre(20, 2, 90, daysHotel);
		Chambre chambre_day30 = new Chambre(30, 1, 90, daysHotel);
		
		
		Hotel euroHotel = new Hotel("EuroHotel",Etoiles.TROIS);
		euroHotel.setAdresse(45, "Rue de la Courge", Ville.Lille);
		Chambre chambre_eur10 = new Chambre(10, 4, 120, euroHotel);
		Chambre chambre_eur20 = new Chambre(20, 2, 120, euroHotel);
		Chambre chambre_eur30 = new Chambre(30, 1, 120, euroHotel)

Agence Disponibles : ("Nom Agence", "login", "motDePasse") : 

		Agence selectour = new Agence("Selectour", "sel_log", "selectour");
		Agence petitbudget = new Agence("Petit Budget", "pet_log", "petitbudget");
		Agence promovacances = new Agence("Promovacances","prom_log", "promovacances");
		Agence odysway = new Agence("Odysway", "ody_log", "odysway");

Pour lancer la version distribuée QUESTION 2 à 3 : 


Placez eclipse dans le répertoire TP_SOAP_CHRISTROPHE_HOUBRON_ETIENNE_MARTIN

Ouvrez eclipse

Cliquez sur file

Import

Une Fenetre s'ouvre cliquez sur Maven puis existing Maven Projects 

Configurer le buildPath pour le projet client et Serveur 
afin d'avoir la jre et le compilateur 1.8 conformément au
WorkFlow SOAP : 

Build Path

Librairies

Edit... 

Alternate JRE puis cliquez sur installed JRES

Une fenetre s'ouvre cliquez sur add et ajouter le fichier : 

Java-se-8u41-ri/bin conformément à l'installation du Workflow SOAP

Fermer les fenêtres choissiez la JavaSE 1.8 comme Execution Environnement



Sur le projet Serveur cliquez sur Server puis vérifier les ports afin
qu'il ne soit pas déjà occupé sur votre ordinateur puis clique
droit sur la class chercher RUN AS 

Et choississez Java Application.

Puis allez sur le projet client après avoir aussi configurer le 
build-path faite de même client droit sur la class CLI RUN AS

Et choississez Java Application.

Enjoy ! 

DATABASE SERVEUR : 
		
		Hotel daysHotel = new Hotel("DaysHotel", Etoiles.DEUX);
		daysHotel.setAdresse("France",10, "Route du Midi", Ville.MONTPELLIER);
		
		daysHotel.getListChambres().addAll(Arrays.asList(
				new Chambre(1, 1, 2, 90, PathForChamberPicture+"pic1.jpg", daysHotel),
				new Chambre(2, 2, 2, 77,  PathForChamberPicture+"pic2.jpg", daysHotel),
				new Chambre(3, 3, 4, 90, PathForChamberPicture+"pic3.jpg", daysHotel)	
				));
		
		daysHotel.getListChambres().get(0).getListReservationsChambre().addAll(Arrays.asList(
				new Reservation(LocalDate.now().format(DATE_TIME_FORMATTER),( LocalDate.now().plusDays(1)).format(DATE_TIME_FORMATTER), 2, Dupond)));

********************************Date Arrivee : Date Aujourd'hui - DateDepart lendemain de la date d'aujourd'hui*************************************************
		
		daysHotel.getListChambres().get(1).getListReservationsChambre().addAll(Arrays.asList(new Reservation("05-01-2023", "08-01-2023", 2, Raïssanna)));
		
		//Instantiation d'objet InfoAgence dans la liste des Agences partenaires
		daysHotel.getListAgencesPartenaire().addAll(Arrays.asList(new infoAgence(1, "sel_log", "selectour", 10),
																	new infoAgence(2, "trip_log", "tripavis", 20),
																    new infoAgence(3, "via_log", "viahotel", 15)));

********************************Hotel La Paix pas de Réservation*************************************************		

		Hotel laPaix = new Hotel("LaPaix", Etoiles.DEUX);
		laPaix.setAdresse("France",20, "Rue de la Paix", Ville.MONTPELLIER);
		
		laPaix.getListChambres().addAll(Arrays.asList(
				new Chambre(4, 10, 5, 225, PathForChamberPicture+"pic4.jpg", laPaix),
				new Chambre(5, 20, 2, 140,  PathForChamberPicture+"pic5.jpg", laPaix),
				new Chambre(6, 3, 2, 160, PathForChamberPicture+"pic6.jpg", laPaix)	
				));

//		
		//Instantiation d'objet InfoAgence dans la liste des Agences partenaires
		laPaix.getListAgencesPartenaire().addAll(Arrays.asList(new infoAgence(1, "sel_log", "selectour", 5),
																	new infoAgence(2, "trip_log", "tripavis", 15),
																    new infoAgence(3, "via_log", "viahotel", 25)));
	
		HotelRepository dayshotelRepo = new HotelRepository(daysHotel);
		HotelRepository lapaixRepo = new HotelRepository(laPaix);
		


DATABASE CLIENT : ("Nom Agence", identifiant "login", "motDePasse") : 


	public static ArrayList<Agence> listAgence = new ArrayList<>(Arrays.asList(
			new Agence("Selectour",1, "sel_log", "selectour"),
			new Agence("TripAvis", 2, "trip_log", "tripavis"),
			new Agence("ViaHotel", 3, "via_log", "viahotel")));






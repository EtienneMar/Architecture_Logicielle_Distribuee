package client.cli.input.processor;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class DateInputProcessorFirstDateArrivee extends ComplexeUserInputProcessor<String> {
	/*Attribut*/
	protected static final LocalDate TODAY = LocalDate.now(); 
	protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	protected LocalDate dateArriveeFormated = null; 
	protected String dateArrivee = "null";
	protected String dateDepart = "null";
	
	//Getter then Setter
	public LocalDate getDateArriveeFormated () { 
		return this.dateArriveeFormated;
	}
	
	public void setDateArriveeFormat(String dateArrivee) {
		this.dateArriveeFormated = LocalDate.parse(dateArrivee, DATE_TIME_FORMATTER);
	}

	public String getDateArrivee() {
		return this.dateArrivee;
	}

	public void setDateArrivee(String dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	/* Constructeur premier date arrivee*/
	
	public DateInputProcessorFirstDateArrivee(Scanner inputReader, String message) {
		super(inputReader, message);
	}
	
	/*Classe interne DateDepart*/
	class DateDepart extends DateInputProcessorFirstDateArrivee{
		/* ATTRIBUTS */ 
		
		public DateDepart(Scanner inputReader, String message) {
			super(inputReader, message);
		}
		
	
		@Override
		protected void setMessage(String message) {
			this.message = message;
		}


		@Override
		protected void setValidityCriterion() {

			isValid = str -> {
				try {
					String valueOfDateDepart = str;
					LocalDate dateDepartFormated= LocalDate.parse(valueOfDateDepart, DATE_TIME_FORMATTER);
					if (dateDepartFormated.getYear() > TODAY.getYear()+1) {
						System.err.println("Désolé mais il n'est pas possible de réserver au delà de l'année "+(TODAY.getYear()+1));
						return false; 
					}
					if (DateInputProcessorFirstDateArrivee.this.dateArriveeFormated.isAfter(dateDepartFormated)) {
						System.err.println("Entrer une date sous la forme jj-mm-yyyy, date qui ne peut être"
								+ "inférieur à la date que vous avez entrée pour votre arrivée "+DateInputProcessorFirstDateArrivee.this.dateArrivee);
						return false;
					}else {			
						return true;
					}
				} catch (DateTimeParseException e) {
					System.err.println("Entrer une date sous la forme jj-mm-yyyy, date qui ne peut être"
					+" inférieur à votre date d'arrivee "+DateInputProcessorFirstDateArrivee.this.dateArrivee);
					return false;
				}
			};
		}

		@Override
		protected void setParser() {
			parser = null; 
			}
		}
	
	//extends of ComplexeUserInputProcessor
	@Override
	protected void setMessage(String message) {
		this.message = message+TODAY.format(DATE_TIME_FORMATTER);
	}
	
	@Override
	protected void setValidityCriterion() {
		isValid = str -> {
			try {	
				String valueOfDateArrivee = str;
				setDateArriveeFormat(valueOfDateArrivee);
				if (dateArriveeFormated.getYear() > TODAY.getYear()+1) {
					System.err.println("Désolé mais il n'est pas possible de réserver au delà de l'année."+(TODAY.getYear()+1));
					return false;
				}
				//|| TODAY.equals(dateArriveeFormated)
				if (TODAY.isAfter(dateArriveeFormated) ) {
					System.err.println("Merci de renseigner une date d'arrivée qui se situe après le "
							+ TODAY.format(DATE_TIME_FORMATTER)  + " date d'aujourd'hui.");
					return false;
				}else {
				//NE PAS ToUCHER PERMET DE SET LA VARIABLE GLOBAL
					setDateArrivee(valueOfDateArrivee);
					addToListDate(valueOfDateArrivee);
					DateDepart dateDepart = new DateDepart(inputReader, 
							"Veuillez entrer une date de départ (jj-mm-yyyy) qui se situe après votre date d'arrivée le : "+getDateArrivee());
					addToListDate(dateDepart.stringProcess());
					return true; 
				//A garder au cas où si cela ne fonctionne pas du côté serveur 
//				return valueOfDateArrivee instanceof String;
				}
			} catch (DateTimeParseException | IOException  e) {
				System.err.println("Entrer une date sous la forme jj-mm-yyyy, date qui ne peut être"
						+" inférieur à la date d'aujourd'hui "+TODAY.format(DATE_TIME_FORMATTER));
				return false;
			} 

		};
	}
			
	

	@Override
	protected void setParser() {
	 parser = null; 
		}
	
	}
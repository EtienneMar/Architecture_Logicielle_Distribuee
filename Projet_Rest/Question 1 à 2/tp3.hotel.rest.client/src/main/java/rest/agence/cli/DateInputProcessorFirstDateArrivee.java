package rest.agence.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateInputProcessorFirstDateArrivee extends ComplexUserInputProcessor<String> {
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
	
	public DateInputProcessorFirstDateArrivee(BufferedReader inputReader, String message) {
		super(inputReader, message);
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

				if (TODAY.isAfter(dateArriveeFormated) || TODAY.equals(dateArriveeFormated)) {
					System.err.println("Merci de renseigner une date d'arrivée qui se situe après le "
							+ TODAY.format(DATE_TIME_FORMATTER)  + " date d'aujourd'hui.");
					return false;
				}else {
				//NE PAS ToUCHER PERMET DE SET LA VARIABLE GLOBAL
					setDateArrivee(valueOfDateArrivee);
					addToListDate(dateArriveeFormated.toString());
					DateDepart dateDepart = new DateDepart(inputReader, 
							"Veuillez entrer une date de départ (jj-mm-yyyy) qui se situe après votre date d'arrivée le : "+getDateArrivee());
					dateDepart.stringProcess();
					addToListDate(dateDepart.getDateDepartFormated().toString());
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
	
	
	/*Classe interne DateDepart*/
	class DateDepart extends DateInputProcessorFirstDateArrivee{
		/* ATTRIBUTS */ 
		protected LocalDate dateDepartFormated = null; 
		
		
		public LocalDate getDateDepartFormated() {
			return dateDepartFormated;
		}


		public void setDateDepartFormated(LocalDate dateDepartFormated) {
			this.dateDepartFormated = dateDepartFormated;
		}


		public DateDepart(BufferedReader inputReader, String message) {
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
					dateDepartFormated= LocalDate.parse(valueOfDateDepart, DATE_TIME_FORMATTER);
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
	

	}
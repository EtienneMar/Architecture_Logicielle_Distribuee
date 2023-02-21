package rest.agence.cli;

import java.io.BufferedReader;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class CardExpirationInputProcessor extends ComplexUserInputProcessor<String> {
	/*Attribut*/
	protected static final YearMonth TODAY_Y_M = YearMonth.now(); 
	protected static final DateTimeFormatter MONTH_YEAR_FORMATTER= DateTimeFormatter.ofPattern("MM/yy");
	protected static YearMonth DATE_EXPIRATION_FORMATTED;
	/* Constructeur */
	
	public CardExpirationInputProcessor(BufferedReader inputReader, String message) {
		super(inputReader, message);
	}

	//extends of ComplexeUserInputProcessor
	@Override
	protected void setMessage(String message) {
		this.message = message+TODAY_Y_M.format(MONTH_YEAR_FORMATTER);
	}
	

	@Override
	protected void setValidityCriterion() {
		isValid = str -> {
			try {
				String valueOfDateExpiration = str;
				DATE_EXPIRATION_FORMATTED = YearMonth.parse(valueOfDateExpiration, MONTH_YEAR_FORMATTER);
				if (TODAY_Y_M.isAfter(DATE_EXPIRATION_FORMATTED) || TODAY_Y_M.equals(DATE_EXPIRATION_FORMATTED)) {
					System.err.println("Merci de renseigner une date d'arrivée qui se situe après le "
							+ TODAY_Y_M + " (Mois et Année en cours).");
					return false;
				}
				return true;
			}catch (DateTimeParseException e) {
				System.err.println("Votre Carte n'est plus acceptable merci de renseigner une carte dont la date d'expiration sous le format mm/yy"
						+"est supérieur au mois et année en cours"+TODAY_Y_M.format(MONTH_YEAR_FORMATTER));
				return false;
			} 
		};
	}
			

	@Override
	protected void setParser() {
	 parser = null; 
		}
	
	}
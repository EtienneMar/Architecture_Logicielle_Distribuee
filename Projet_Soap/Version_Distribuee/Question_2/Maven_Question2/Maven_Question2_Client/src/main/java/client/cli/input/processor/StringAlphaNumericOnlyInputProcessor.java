package client.cli.input.processor;



import java.util.Scanner;

import org.glassfish.pfl.dynamic.generator.Value;

public class StringAlphaNumericOnlyInputProcessor extends ComplexeUserInputProcessor<String> {


	public StringAlphaNumericOnlyInputProcessor(Scanner inputReader, String message) {
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
				String value = str;
				if (! value.matches("[0-9]+")) {
					System.err.println("Erreur une chaine de caractère ne peut être compris qu'entre a et z "
							+ "il ne peut y avoir de caractères spéciaux ou de chiffre");
					return false; 
				}else {
				return value instanceof String;} 
			} catch (Exception e) {
				return false;
			}
		};
	}

	@Override
	protected void setParser() {
	}
}








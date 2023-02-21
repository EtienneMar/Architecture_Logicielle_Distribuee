package client.cli.input.processor;


import java.util.Scanner;

import org.glassfish.pfl.dynamic.generator.Value;

public class StringOnlySpaceExceptionInputProcessor extends ComplexeUserInputProcessor<String> {


	public StringOnlySpaceExceptionInputProcessor(Scanner inputReader, String message) {
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
				if (value.matches("[\\W]+") || value.matches("[\\w]+")) {
					return value instanceof String;
				}else {
					System.err.println("Erreur votre chaine de caractère ne peut contenir d'espace vide ");
					return false; } 
			} catch (Exception e) {
				return false;
			}
		};
	}

	@Override
	protected void setParser() {
	}


}







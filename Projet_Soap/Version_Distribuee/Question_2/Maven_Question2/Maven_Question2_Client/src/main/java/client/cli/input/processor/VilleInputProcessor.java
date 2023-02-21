package client.cli.input.processor;

import java.util.Scanner;

import web.service.client.consultation.wsimport.Ville;


public class VilleInputProcessor extends ComplexeUserInputProcessor<String>{


	public VilleInputProcessor(Scanner inputReader, String message) {
		super(inputReader, message);
		// TODO Auto-generated constructor stub
	}


	
	@Override
	protected void setMessage(String message) {
		this.message = message;
		
	}
	
	protected void setValidityCriterion() {
		isValid = str -> {
			try {
				String value = str;
				Ville.valueOf(value.toUpperCase());
				return value instanceof String; 
			}  catch (IllegalArgumentException e) {
				System.err.println("Merci de choisir une ville parmi la liste des villes proposées");
				return false;
			}
		};
	}

	@Override
	protected void setParser() {
		parser = null;
		}
	}



package client.cli.input.processor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public abstract class ComplexeUserInputProcessor<T> {
	/* ATTRIBUTES */
	protected Scanner inputReader;
	protected String message;
	protected Predicate<String> isValid;
	protected Method parser;
	protected T parameter;
	//Optimisation possible en précisant que c'est une liste de deux éléments
	protected ArrayList<String> date = new ArrayList<>();
	protected ArrayList<Float> priceIntervalle = new ArrayList<>();
	
	/* CONSTRUCTOR */
	public ComplexeUserInputProcessor(Scanner inputReader, String message) {
		this.inputReader = inputReader;
		setMessage(message);
		setValidityCriterion();
		setParser();
	}
	
	/* METHODS */
	
	protected abstract void setMessage(String message);
	protected abstract void setValidityCriterion();
	protected abstract void setParser();
	protected void addToListDate(String addDate) {date.add(addDate);}
	protected void addToListPriceIntervalle(Float priceAdd) {priceIntervalle.add(priceAdd);}
	
	/* IntegerProcess Retourne un int dans le cli */
	
	public T integerProcess() throws IOException {
		System.out.println(message);
		String userInput = inputReader.nextLine();
		while (!isValid.test(userInput)) {
			System.err.println("Désolé, votre précédente donnée entrée est fausse, veuillez réessayer.");
			System.out.println();
			System.out.println(message);
			userInput = inputReader.nextLine();
		}
		try {
			parameter = (T) parser.invoke(null, userInput);
		} catch (SecurityException | IllegalAccessException |
				IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return parameter;
	}
	
	/* FloatProcess */
	public Float floatProcess() throws IOException {
		System.out.println(message);
		String userInput = inputReader.nextLine();
		while (!isValid.test(userInput)) {
			System.err.println("Désolé, votre précédente donnée entrée est fausse, veuillez réessayer.");
			System.out.println();
			System.out.println(message);
			userInput = inputReader.nextLine();
		}
		try {
			parameter = (T) parser.invoke(null, userInput);
		} catch (SecurityException | IllegalAccessException |
				IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return Float.parseFloat(userInput);
	}
	
	
	public ArrayList<Float> prixIntervalle() throws IOException {
		System.out.println(message);
		String userInput = inputReader.nextLine();
		while (!isValid.test(userInput)) {
			System.err.println("Désolé, votre précédente donnée entrée est fausse, veuillez réessayer.");
			System.out.println();
			System.out.println(message);
			userInput = inputReader.nextLine();
		}
		try {
			parameter = (T) parser.invoke(null, userInput);
		} catch (SecurityException | IllegalAccessException |
				IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return priceIntervalle;
	}
	
	

	
	/*ArrayList Date Test*/ 
	
	public ArrayList<String> date() throws IOException {
		System.out.println(message);
		String userInput = inputReader.nextLine();
		while (!isValid.test(userInput)) {
			System.err.println("Désolé, votre précédente donnée entrée est fausse, veuillez réessayer.");
			System.out.println();
			System.out.println(message);
			userInput = inputReader.nextLine();
		}
		
		return date;	
	}
	
	/* Retourne une String, le userinput dans le CLI s'il est valide*/ 
	
	public String stringProcess() throws IOException {
		System.out.println(message);
		String userInput = inputReader.nextLine();
		while (!isValid.test(userInput)) {
			System.err.println("Désolé, votre précédente donnée entrée est fausse, veuillez réessayer.");
			System.out.println();
			System.out.println(message);
			userInput = inputReader.nextLine();
		}
		return userInput;	
	}
	
	/* Ville Processor */
	public String villeprocess() throws IOException {
		System.out.println(message);
		String userInput = inputReader.nextLine();
		while (!isValid.test(userInput)) {
			System.err.println("Désolé, votre précédente donnée entrée est fausse, veuillez réessayer.");
			System.out.println();
			System.out.println(message);
			userInput = inputReader.nextLine();
		}
		return userInput.toUpperCase();	
	}
	
	
}


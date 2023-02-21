package rest.agence.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.function.Predicate;

public abstract class ComplexUserInputProcessor<T> {
	/* ATTRIBUTES */
	protected String message;
	protected BufferedReader inputReader;
	protected Method parser;
	protected Predicate<String> isValid;
	
	//Optimisation possible en précisant que c'est une liste de deux éléments
	protected ArrayList<String> date = new ArrayList<>();
	protected ArrayList<Float> priceIntervalle = new ArrayList<>();
	
	
	protected T parameter;
	/* CONSTRUCTOR */
	public ComplexUserInputProcessor(BufferedReader inputReader, String message) {
		this.inputReader = inputReader;
		setMessage(message);
		setParser();
		setValidityCriterion();
	}
	/* METHODS */
	protected abstract void setMessage(String message);
	protected abstract void setValidityCriterion();
	protected abstract void setParser();
	
	protected void addToListDate(String addDate) {date.add(addDate);}
	protected void addToListPriceIntervalle(Float priceAdd) {priceIntervalle.add(priceAdd);}
	
	
	public T process() throws IOException {
		System.out.println(message);
		String userInput = inputReader.readLine();
		while (!isValid.test(userInput)) {
			System.err.println("Sorry, wrong input. Please try again.");
			System.out.println();
			System.out.println(message);
			userInput = inputReader.readLine();
		}
		try {
			parameter = (T) parser.invoke(null, userInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return parameter;
	}
	
	/*ArrayList Date Test*/ 
	
	public ArrayList<String> date() throws IOException {
		System.out.println(message);
		String userInput = inputReader.readLine();
		while (!isValid.test(userInput)) {
			System.err.println("Désolé, votre précédente donnée entrée est fausse, veuillez réessayer.");
			System.out.println();
			System.out.println(message);
			userInput = inputReader.readLine();
		}
		
		return date;	
	}
	
	/* FloatProcess */
	public Float floatProcess() throws IOException {
		System.out.println(message);
		String userInput = inputReader.readLine();
		while (!isValid.test(userInput)) {
			System.err.println("Désolé, votre précédente donnée entrée est fausse, veuillez réessayer.");
			System.out.println();
			System.out.println(message);
			userInput = inputReader.readLine();
		}
		try {
			parameter = (T) parser.invoke(null, userInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Float.parseFloat(userInput);
	}
	
	public ArrayList<Float> prixIntervalle() throws IOException {
		System.out.println(message);
		String userInput = inputReader.readLine();
		while (!isValid.test(userInput)) {
			System.err.println("Désolé, votre précédente donnée entrée est fausse, veuillez réessayer.");
			System.out.println();
			System.out.println(message);
			userInput = inputReader.readLine();
		}
		try {
			parameter = (T) parser.invoke(null, userInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priceIntervalle;
	}
	
	public String stringProcess() throws IOException {
		System.out.println(message);
		String userInput = inputReader.readLine();
		while (!isValid.test(userInput)) {
			System.err.println("Désolé, votre précédente donnée entrée est fausse, veuillez réessayer.");
			System.out.println();
			System.out.println(message);
			userInput = inputReader.readLine();
		}
		return userInput;	
	}
}

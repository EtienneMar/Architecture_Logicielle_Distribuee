package web.service.model;


import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class CarteBancaire {
	private String numeroCarte;
	private YearMonth dateExpiration;
	private int cryptogramme;
	
	//********************** Constructeurs **************************
	
	public CarteBancaire () {}
	
	public CarteBancaire (String numeroCarte, String dateExpiration, int cryptogramme) {
		this.setNumeroCarte(numeroCarte);
		this.setDateExpiration(dateExpiration);
		this.setCryptogramme(cryptogramme);
	}
	
	//********************** accesseurs **************************
	
	public String getNumeroCarte() {return numeroCarte;}
	
	public void setNumeroCarte(String numeroCarte) {
		if (numeroCarte.matches("[0-9]+") && numeroCarte.length() == 16) {
			this.numeroCarte = numeroCarte;
		}else {System.out.println("Veuillez entrée un numéro de carte valide");}
	}
	
	public YearMonth getDateExpiration() {return dateExpiration;}
	
	public void setDateExpiration(String dateExpiration) {
		
		DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM/yy");
		YearMonth today = YearMonth.now();
		YearMonth dateExpirationFormatted = YearMonth.parse(dateExpiration, monthYearFormatter);
		
		if (today.isBefore(dateExpirationFormatted)) {
			this.dateExpiration = YearMonth.parse(dateExpiration, monthYearFormatter);
		}else {System.out.println("Votre carte à expirer\n"
								+ "Merci de saisir une date au format mm/yy après le "+yearToStringRes(today));
			}
	}
	
	public int getCryptogramme() {return cryptogramme;}
	
	public void setCryptogramme(int cryptogramme) {
		if (cryptogramme <= 999) {
			this.cryptogramme = cryptogramme;
		}else {System.out.println("Merci de saisir une cryptogramme à 3 chiffre inférieur ou égal à 999");}
	}
	
	//Méthode pour transformer une YearMonth en String sous la forme MM/YYYY
	public String yearToStringRes(YearMonth yearMonthI) {
		DateTimeFormatter DateToString = DateTimeFormatter.ofPattern("MM/yyyy");
		return yearMonthI.format(DateToString);
	}
	
	public String toString() {
		return "CarteBancaire [Numero de Carte=" + this.getNumeroCarte() + ", dateExpiration=" + yearToStringRes(this.getDateExpiration()) + ", cryptogramme="
				+ this.getCryptogramme() + "]";
	}
	
	
}
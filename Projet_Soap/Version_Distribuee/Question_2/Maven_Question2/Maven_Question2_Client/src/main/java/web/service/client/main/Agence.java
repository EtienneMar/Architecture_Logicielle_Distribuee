package web.service.client.main;

public class Agence {
	//Attributs de la Classe Agence:
	private String nomAgence;
	private int identifiantAgence;
	private String login;
	private String password;
	
	//Constructeur le la classe Agence:
	public Agence(String nomAgence, int identifiantAgence, String logginI, String passwordI) {
		this.setIdentifiantAgence(identifiantAgence);
		this.setNomAgence(nomAgence);
		this.setLogin(logginI);
		this.setPassword(passwordI);
	}

	//Accesseurs
	
	public String getNomAgence() {
		return nomAgence;
	}

	public void setNomAgence(String nomAgence) {
		this.nomAgence = nomAgence;
	}
	
	public int getIdentifiantAgence() {
		return this.identifiantAgence;
	}
	public void setIdentifiantAgence(int identifiantAgence) {
		this.identifiantAgence = identifiantAgence;
	}
	public String getLogin() {
		return this.login;
	}
	public void setLogin(String loginI) {
		this.login = loginI;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String passwordI) {
		this.password = passwordI;
	}
	

	

}

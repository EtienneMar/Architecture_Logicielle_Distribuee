package WebService;

public class Agence {
	//Attributs de la Classe Agence:
	private String nomAgence;
	private String loggin;
	private String password;
	
	//Constructeur le la classe Agence:
	public Agence(String nomI, String logginI, String passwordI) {
		this.setNomAgence(nomI);
		this.setLoggin(logginI);
		this.setPassword(passwordI);
	}
	
	
	
	//Accesseurs
	public String getNomAgence() {
		return this.nomAgence;
	}
	public void setNomAgence(String nomAgenceI) {
		this.nomAgence = nomAgenceI;
	}
	public String getLoggin() {
		return this.loggin;
	}
	public void setLoggin(String logginI) {
		this.loggin = logginI;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String passwordI) {
		this.password = passwordI;
	}
	

	

}

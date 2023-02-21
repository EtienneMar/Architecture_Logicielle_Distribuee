package web.service.model;

public class infoAgence {
	//Attributs de la Classe Agence:
	private int identifiantAgence;
	private String login;
	private String password;
	private float coeffPromotion; 
	
	//Constructeur le la classe Agence:
	public infoAgence(int identifiantAgence, String logginI, String passwordI, float coefficientPromotion) {
		this.setIdentifiantAgence(identifiantAgence);
		this.setLogin(logginI);
		this.setPassword(passwordI);
		this.setCoeffPromotion(coefficientPromotion);
	}

	//Accesseurs
	
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

	public float getCoeffPromotion() {
		return coeffPromotion;
	}

	public void setCoeffPromotion(float coeffPromotion) {
		this.coeffPromotion = (100-coeffPromotion)/100;
	}

	@Override
	public String toString() {
		return "infoAgence [identifiantAgence=" + identifiantAgence + ", login=" + login + ", password=" + password
				+ ", coeffPromotion=" + coeffPromotion + "]";
	}
	

	

}

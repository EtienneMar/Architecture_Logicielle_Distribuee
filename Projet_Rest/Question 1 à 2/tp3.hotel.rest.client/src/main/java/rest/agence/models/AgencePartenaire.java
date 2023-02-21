package rest.agence.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencePartenaire {
	
	//Attributs de la Classe Agence:
	
	private int idAgence;
	
	private String login;
	
	private String password;
		
	//Constructeur le la classe Agence:
	
	public AgencePartenaire() {}

	public AgencePartenaire(int idAgence, String login, String password) {
		this.setIdAgence(idAgence);
		this.setLogin(login);
		this.setPassword(password);
	}

	//Accesseurs
	public int getIdAgence() {
		return this.idAgence;
	}


	public void setIdAgence(int idAgence) {
		this.idAgence = idAgence;
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

	@Override
	public String toString() {
		return "AgencePartenaire [idAgence=" + idAgence + ", login=" + login + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAgence, login, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgencePartenaire other = (AgencePartenaire) obj;
		return idAgence == other.idAgence && Objects.equals(login, other.login)
				&& Objects.equals(password, other.password);
	}
	
	
	

}

package agence.rest.server.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "agence")
public class Agence {
	
	//Attributs de la Classe Agence:
	
	@Id
	@Column(name = "identifiant")
	private int identifiant;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "password")
	private String password;

	//Constructeur le la classe Agence:
	
	public Agence() {};
	
	public Agence(int idAgence, String nomAgence, String login, String password) {
		this.setIdentifiant(idAgence);
		this.setNom(nomAgence);
		this.setLogin(login); 
		this.setPassword(password);
	}


	//Accesseurs
	
	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//Méthodes

	@Override
	public String toString() {
		return "Agence [identifiant=" + identifiant + ", nom=" + nom + ", login=" + login + ", password=" + password
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(identifiant, login, nom, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agence other = (Agence) obj;
		return identifiant == other.identifiant && Objects.equals(login, other.login) && Objects.equals(nom, other.nom)
				&& Objects.equals(password, other.password);
	}		

}





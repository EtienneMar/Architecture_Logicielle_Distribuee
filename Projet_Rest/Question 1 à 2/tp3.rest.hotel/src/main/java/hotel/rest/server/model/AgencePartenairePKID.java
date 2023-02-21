package hotel.rest.server.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AgencePartenairePKID implements Serializable {

	//Attributs
	/**
	* 
	*/
	private static final long serialVersionUID = -9200517290212471520L;

	@Column(name = "login_agence", length = 50)
	private String loginAgence;

	@Column(name = "password_agence", length = 50)
	private String passwordAgence;

	//Accesseur
	public String getLoginAgence() {
		return loginAgence;
	}

	public String getPasswordAgence() {
		return passwordAgence;
	}

	public void setLoginAgence(String loginAgence) {
		this.loginAgence = loginAgence;
	}

	public void setPasswordAgence(String passwordAgence) {
		this.passwordAgence = passwordAgence;
	}

	//Methode
	@Override
	public String toString() {
		return "AgencePartenairePKID [loginAgence=" + loginAgence + ", passwordAgence=" + passwordAgence + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(loginAgence, passwordAgence);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgencePartenairePKID other = (AgencePartenairePKID) obj;
		return Objects.equals(loginAgence, other.loginAgence) && Objects.equals(passwordAgence, other.passwordAgence);
	}

}

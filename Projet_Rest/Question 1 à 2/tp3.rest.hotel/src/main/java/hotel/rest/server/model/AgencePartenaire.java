package hotel.rest.server.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "agence_partenaire")
public class AgencePartenaire {
	
	//Attributs de la Classe Agence:

	@Column(name = "id_agence")
	private int idAgence;
	
	@EmbeddedId
	private AgencePartenairePKID agencePartenairePKID;
	
	@Column(name = "coeff_promotion")
	private float coeffPromotion; 
	
	
	@ManyToOne(
	fetch = FetchType.EAGER
	)
	@JoinColumn(name ="id_nom_hotel", foreignKey = @ForeignKey(name = "FK_nom_Hotel", foreignKeyDefinition = "foreign key chambre(id_nom_hotel) references hotel (nom)"))
	@JsonBackReference
	private Hotel hotel; 
	
	//Constructeur le la classe Agence:
	
	public AgencePartenaire() {}

	public AgencePartenaire(int idAgence, AgencePartenairePKID agencePartenairePKID, float coeffPromotion,
			Hotel hotel) {
		this.setIdAgence(idAgence);
		this.setAgencePartenairePKID(agencePartenairePKID); 
		this.setCoeffPromotion(coeffPromotion);
		this.setHotel(hotel);
	}

	//Accesseurs
	
	public int getIdAgence() {
		return this.idAgence;
	}

	public void setIdAgence(int idAgence) {
		this.idAgence = idAgence;
	}

	public AgencePartenairePKID getAgencePartenairePKID() {
		return agencePartenairePKID;
	}

	public void setAgencePartenairePKID(AgencePartenairePKID agencePartenairePKID) {
		this.agencePartenairePKID = agencePartenairePKID;
	}

	public float getCoeffPromotion() {
		return coeffPromotion;
	}

	public void setCoeffPromotion(float coeffPromotion) {
		this.coeffPromotion = (100-coeffPromotion)/100;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	//Méthodes
	
	@Override
	public String toString() {
		return "AgencePartenaire [idAgence=" + idAgence + ", agencePartenairePKID=" + agencePartenairePKID.toString()
				+ ", coeffPromotion=" + coeffPromotion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(agencePartenairePKID, coeffPromotion, idAgence);
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
		return Objects.equals(agencePartenairePKID, other.agencePartenairePKID)
				&& Float.floatToIntBits(coeffPromotion) == Float.floatToIntBits(other.coeffPromotion)
				&& idAgence == other.idAgence;
	}
	
}

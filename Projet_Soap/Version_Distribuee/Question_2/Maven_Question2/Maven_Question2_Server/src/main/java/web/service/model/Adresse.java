package web.service.model;


public class Adresse {
	private String pays;
	private int numRue;
	private String rue;
	private Ville ville;
	private String lieudit;
	private String gpxPosition;

	//********************** Constructeurs **************************
	public Adresse() {}

	//Constructeur voirie
	public Adresse(String pays, int numRueI, String rueI, Ville villeI, String gpxPosition) {
		this.setPays(pays);
		this.setNumRue(numRueI);
		this.setRue(rueI);
		this.setVille(villeI);
		this.setGpxPosition(gpxPosition);
	}


    //Constructeur lieudit
    public Adresse(String pays, String lieudit, Ville villeI, String gpxPosition) {
    	this.setPays(pays);
		this.setLieudit(lieudit);
		this.setVille(villeI);
		this.setGpxPosition(gpxPosition);
    }

	//********************** accesseurs **************************


	public String getPays() {return pays;}
	public void setPays(String pays) {this.pays = pays;}

	public String getGpxPosition() {return gpxPosition;}

	public void setGpxPosition(String gpxPosition) {this.gpxPosition = gpxPosition;}

	public int getNumRue() {return this.numRue;}
	public void setNumRue(int numRueI) {
		if (numRueI >= 1) {
			this.numRue = numRueI;
		}
		else{
			System.err.println("Le numéro de la rue doit être supérieur à 0");
		}
	}

	public String getRue() {return this.rue;}
	public void setRue(String rueI) {this.rue = rueI;}

	public Ville getVille() {return this.ville;}
	public void setVille(Ville villeI) {this.ville = villeI;}

	public String getLieudit() {return lieudit;}
	public void setLieudit(String lieudit) {this.lieudit = lieudit;}

	//Methode toString modifiée MAYBE A MODIFIER 
	public String toString() {
		String str = "";
		if (this.getLieudit()==null) {
			str += String.format("Adresse : %d %s, %s", this.getNumRue(), this.getRue(), this.getVille());
			if (this.getPays()!=null) {
				str += String.format(", Pays : %s",this.getPays());
				if (this.getGpxPosition()!=null) {
					str += String.format(", Position GPS : %s", this.getGpxPosition());
				}
			}
		}else {
			str = String.format("Lieudit/Ville : %s / %s", this.getLieudit(), this.getVille());
			if (this.getPays()!=null) {
				str += String.format(", Pays : %s",this.getPays());
				if (this.getGpxPosition()!=null) {
					str += String.format(", Position GPS : %s", this.getGpxPosition());
				}
			}
		}
		return str;

	}
}


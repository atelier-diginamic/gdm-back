package dev.controller.collegue;



//classe qui heberge les informations 

public class NatureRequestDto {

	
	private String nom;

	private boolean missionFacturee;
	
	private boolean versementPrime;
	
	private String tjm;
	
	private String pourcentagePrime;
	private String dateFin;
	
	
	public NatureRequestDto() {
		super();
	}


	public NatureRequestDto(String nom, boolean missionFacturee, boolean versementPrime, String tjm,
			String pourcentagePrime, String dateFin) {
		super();
		this.nom = nom;
		this.missionFacturee = missionFacturee;
		this.versementPrime = versementPrime;
		this.tjm = tjm;
		this.pourcentagePrime = pourcentagePrime;
		this.dateFin = dateFin;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public boolean isMissionFacturee() {
		return missionFacturee;
	}


	public void setMissionFacturee(boolean missionFacturee) {
		this.missionFacturee = missionFacturee;
	}


	public boolean isVersementPrime() {
		return versementPrime;
	}


	public void setVersementPrime(boolean versementPrime) {
		this.versementPrime = versementPrime;
	}


	public String getTjm() {
		return tjm;
	}


	public void setTjm(String tjm) {
		this.tjm = tjm;
	}


	public String getPourcentagePrime() {
		return pourcentagePrime;
	}


	public void setPourcentagePrime(String pourcentagePrime) {
		this.pourcentagePrime = pourcentagePrime;
	}


	public String getDateFin() {
		return dateFin;
	}


	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	
	
	
	
	
	
	
	
}

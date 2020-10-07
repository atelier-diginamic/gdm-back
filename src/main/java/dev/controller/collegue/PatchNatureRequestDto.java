package dev.controller.collegue;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

public class PatchNatureRequestDto {
	
	@NotNull
	private String nom;
	@NotNull
	private boolean missionFacturee;
	@NotNull
	private boolean versementPrime;
    @Positive
	private BigDecimal tjm;
    @Positive
	private BigDecimal pourcentagePrime;
    
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
	public BigDecimal getTjm() {
		return tjm;
	}
	public void setTjm(BigDecimal tjm) {
		this.tjm = tjm;
	}
	public BigDecimal getPourcentagePrime() {
		return pourcentagePrime;
	}
	public void setPourcentagePrime(BigDecimal pourcentagePrime) {
		this.pourcentagePrime = pourcentagePrime;
	}
   
   
}
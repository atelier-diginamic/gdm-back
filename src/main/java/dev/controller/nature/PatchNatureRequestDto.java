package dev.controller.nature;

import java.math.BigDecimal;

import com.sun.istack.NotNull;

public class PatchNatureRequestDto {

	@NotNull
	private String nom;
	@NotNull
	private boolean missionFacturee;
	@NotNull
	private boolean versementPrime;

	private BigDecimal tjm;

	private BigDecimal pourcentagePrime;

	private BigDecimal plafond;

	private boolean plafondDepassable;

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

	public BigDecimal getPlafond() {
		return plafond;
	}

	public void setPlafond(BigDecimal plafond) {
		this.plafond = plafond;
	}

	public boolean isPlafondDepassable() {
		return plafondDepassable;
	}

	public void setPlafondDepassable(boolean plafondDepassable) {
		this.plafondDepassable = plafondDepassable;
	}

}
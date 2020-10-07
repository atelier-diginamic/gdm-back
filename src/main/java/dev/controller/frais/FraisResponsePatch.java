package dev.controller.frais;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FraisResponsePatch {
	

	private LocalDate date;

	private String natureFrais;

	private BigDecimal montantFrais;

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the natureFrais
	 */
	public String getNatureFrais() {
		return natureFrais;
	}

	/**
	 * @param natureFrais the natureFrais to set
	 */
	public void setNatureFrais(String natureFrais) {
		this.natureFrais = natureFrais;
	}

	/**
	 * @return the montantFrais
	 */
	public BigDecimal getMontantFrais() {
		return montantFrais;
	}

	/**
	 * @param montantFrais the montantFrais to set
	 */
	public void setMontantFrais(BigDecimal montantFrais) {
		this.montantFrais = montantFrais;
	}
	
	
	

}

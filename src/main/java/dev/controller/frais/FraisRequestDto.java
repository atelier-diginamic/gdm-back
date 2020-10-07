package dev.controller.frais;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;


public class FraisRequestDto {
	
	// contrainte à faire dans le front ?
	
	@NotNull
	// contrainte à faire dans le front ?
	private LocalDate date;
	
	private String natureFrais;
	@Positive
	private BigDecimal montant;
	

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
	 * @return the montant
	 */
	public BigDecimal getMontant() {
		return montant;
	}
	/**
	 * @param montant the montant to set
	 */
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	

}

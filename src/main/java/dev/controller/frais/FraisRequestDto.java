package dev.controller.frais;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sun.istack.NotNull;

import dev.domain.Nature;

public class FraisRequestDto {
	
	@NotNull
	private LocalDate date;
	private String natureFrais;
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

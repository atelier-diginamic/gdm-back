package dev.controller.frais;

import java.math.BigDecimal;
import java.time.LocalDate;

import dev.domain.Frais;

public class FraisResponseDto {

	
	private Integer id;
	private LocalDate date;

	private String natureFrais;

	private BigDecimal montantFrais;
	
	public FraisResponseDto(Frais frais) {
		id = frais.getId();
		date = frais.getDate();
		natureFrais = frais.getNatureFrais();
		montantFrais = frais.getMontantFrais();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

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

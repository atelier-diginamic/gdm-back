package dev.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Frais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate date;

	private String natureFrais;

	private BigDecimal montantFrais;

	/**
	 * 
	 */
	public Frais() {
	}

	/**
	 * @param id
	 * @param date
	 * @param natureFrais
	 * @param montantFrais
	 */
	public Frais(LocalDate date, String natureFrais, BigDecimal montantFrais) {
		this.date = date;
		this.natureFrais = natureFrais;
		this.montantFrais = montantFrais;
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

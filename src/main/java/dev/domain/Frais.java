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
	private LocalDate dateDebut;
	private LocalDate dateFin;
// class Nature
	private String nature;
	private String villeDepart;
	private String villeArrivee;
	private String transport;
	private BigDecimal montantFrais;

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
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * @param nature the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/**
	 * @return the villeDepart
	 */
	public String getVilleDepart() {
		return villeDepart;
	}

	/**
	 * @param villeDepart the villeDepart to set
	 */
	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	/**
	 * @return the villeArrivee
	 */
	public String getVilleArrivee() {
		return villeArrivee;
	}

	/**
	 * @param villeArrivee the villeArrivee to set
	 */
	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	/**
	 * @return the transport
	 */
	public String getTransport() {
		return transport;
	}

	/**
	 * @param transport the transport to set
	 */
	public void setTransport(String transport) {
		this.transport = transport;
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

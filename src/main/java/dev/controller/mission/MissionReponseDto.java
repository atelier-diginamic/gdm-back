package dev.controller.mission;

import java.math.BigDecimal;
import java.time.LocalDate;

import dev.domain.Mission;
import dev.domain.Statut;

public class MissionReponseDto {

	private Integer id;

	private LocalDate dateDebut;

	private LocalDate dateFin;

	private String nomNature;

	private String villeDepart;

	private String villeArrivee;

	private String transport;

	private Statut statut;

	private BigDecimal prime;

	public MissionReponseDto(Mission mission) {
		id = mission.getId();
		dateDebut = mission.getDateDebut();
		dateFin = mission.getDateFin();
		nomNature = mission.getNature().getNom();
		villeDepart = mission.getVilleDepart();
		villeArrivee = mission.getVilleArrivee();
		transport = mission.getTransport();
		statut = mission.getStatut();
		prime = mission.getPrime();

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
	 * @return the nomNature
	 */
	public String getNomNature() {
		return nomNature;
	}

	/**
	 * @param nomNature the nomNature to set
	 */
	public void setNomNature(String nomNature) {
		this.nomNature = nomNature;
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
	 * @return the statut
	 */
	public Statut getStatut() {
		return statut;
	}

	/**
	 * @param statut the statut to set
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	/**
	 * @return the prime
	 */
	public BigDecimal getPrime() {
		return prime;
	}

	/**
	 * @param prime the prime to set
	 */
	public void setPrime(BigDecimal prime) {
		this.prime = prime;
	}

}

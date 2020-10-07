package dev.controller.mission;

import java.time.LocalDate;

import dev.domain.Mission;

public class MissionReponseDto {

	private LocalDate dateDebut;

	private LocalDate dateFin;

	private String nomNature;

	private String villeDepart;

	private String villeArrivee;

	private String transport;

	public MissionReponseDto(Mission mission) {
		this.dateDebut = mission.getDateDebut();
		this.dateFin = mission.getDateFin();
		this.nomNature = mission.getNature().getNom();
		this.villeDepart = mission.getVilleDepart();
		this.villeArrivee = mission.getVilleArrivee();
		this.transport = mission.getTransport();
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

}

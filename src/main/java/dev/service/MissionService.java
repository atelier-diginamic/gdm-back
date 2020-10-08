package dev.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.Statut;
import dev.repository.MissionRepository;

@Service
public class MissionService {

	private MissionRepository missionRepossitory;

	public MissionService(MissionRepository missionRepossitory) {
		this.missionRepossitory = missionRepossitory;
	}

	public List<Mission> getList() {

		return missionRepossitory.findAll();
	}

	public Mission creerMission(Mission mission) {

		return missionRepossitory.save(mission);
	}

	public List<Mission> listMissions(Long idCollegue) {
		return missionRepossitory.findAllByIdCollegue(idCollegue);

	}

	@Transactional
	public Mission updateCollegue(Integer id, LocalDate dateDebut, LocalDate dateFin, String villeDepart,
			String villeArrivee, String transport, Nature findByNom, Statut statut) {

		missionRepossitory.update(id, dateDebut, dateFin, villeDepart, villeArrivee, transport, findByNom, statut);

		return missionRepossitory.findById(id)
				.orElseThrow(() -> new RuntimeException("erreur : actualisation Mission"));
	}

	@Transactional
	public void traitementNuit() {

		for (Mission m : missionRepossitory.findAll()) {

			if (m.getStatut().equals(Statut.INITIALE)) {
				missionRepossitory.updateStatut(m.getId(), Statut.EN_ATTENTE_VALIDATION);

				// TODO envoie un mail au manager
			}

			if (m.getDateFin().isBefore(LocalDate.now())) {

				// TODO Calcule de la deduction issue #18

				// déduction = somme des frais - (plafond de frais)*(nombre de jours de la
				// mission)
				// le montant de la prime final avec prise en compte de cette déduction est
				// calculé par le traitement de nuit.
				BigDecimal deduction = BigDecimal.ZERO;

				Period period = Period.between(m.getDateDebut(), m.getDateFin());
				int diff = period.getDays();
				BigDecimal joursTravaillés = new BigDecimal(diff);
				BigDecimal tjm = m.getNature().getTjm();
				BigDecimal pourcentagePrime = m.getNature().getPourcentagePrime().divide(new BigDecimal("100"));

				// Prime = (nombre de jours travaillés)* TJM * %Prime/100 - déduction
				BigDecimal prime = joursTravaillés.multiply(tjm.multiply(pourcentagePrime).subtract(deduction));

				missionRepossitory.updatePrime(m.getId(), prime);
			}
		}

	}

	public List<Mission> listMissionsManager(Long idManager) {

		return missionRepossitory.findAllByIdManager(idManager, Statut.EN_ATTENTE_VALIDATION);
	}

	@Transactional
	public void acceptationMission(int id, boolean valide) {
		if (valide) {
			missionRepossitory.updateStatut(id, Statut.VALIDEE);
		} else {
			missionRepossitory.updateStatut(id, Statut.REJETEE);
		}

	}

}

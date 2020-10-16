package dev.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.domain.Collegue;

import dev.domain.Frais;
import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.Statut;
import dev.mailSender.EmailService;
import dev.repository.FraisRepository;
import dev.repository.MissionRepository;

@Service
public class MissionService {

	private MissionRepository missionRepossitory;
	private FraisRepository fraisRepository;
	private EmailService emailService;

	public MissionService(MissionRepository missionRepossitory, FraisRepository fraisRepository,
			EmailService emailService) {
		this.missionRepossitory = missionRepossitory;
		this.fraisRepository = fraisRepository;
		this.emailService = emailService;
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
	public void traitementNuit() throws Exception {
		emailService.sendEmail("hjwc86@gmail.com");
		for (Mission m : missionRepossitory.findAll()) {

			if (m.getStatut().equals(Statut.INITIALE)) {
				missionRepossitory.updateStatut(m.getId(), Statut.EN_ATTENTE_VALIDATION);

				// mailSender.sendEmail(m.getCollegue().getManager().getEmail());
				emailService.sendEmail("hjwc86@gmail.com");

			}

			System.out.println("Done");

			if (m.getDateFin().isBefore(LocalDate.now())) {

				// Calcule de la deduction issue #18

				Period period = Period.between(m.getDateDebut(), m.getDateFin());
				int diff = period.getDays();
				BigDecimal joursTravaillés = new BigDecimal(diff);
				BigDecimal sommeFrais = BigDecimal.ZERO;
				for (Frais f : fraisRepository.FindAllByIdMission(m.getId())) {
					sommeFrais.add(f.getMontantFrais());
				}

				// déduction = somme des frais - (plafond de frais)*(nombre de jours de la
				// mission)
				// le montant de la prime final avec prise en compte de cette déduction est
				// calculé par le traitement de nuit.
				BigDecimal deduction = sommeFrais.subtract(m.getNature().getPlafond().multiply(joursTravaillés));

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

	

	public List <Mission> getByNatureId (Integer idNature){
		return missionRepossitory.findByNatureId(idNature);
	}
	

	@Transactional
	public Optional<Mission> getMission(Integer id) {
		return missionRepossitory.findById(id);

	}

	public List<Mission> delateMission(Integer id) {
		Mission mission = missionRepossitory.findById(id)
				.orElseThrow(() -> new RuntimeException("erreur :suppresion de Mission"));

		missionRepossitory.delete(mission);

		return listMissions(mission.getCollegue().getId());
	}


	@Transactional
	public void updateMission(int i, Collegue col6) {
		missionRepossitory.updateMission(i, col6);

	}


}

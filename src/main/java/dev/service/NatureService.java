package dev.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.controller.nature.PatchNatureRequestDto;
import dev.domain.Mission;
import dev.domain.Nature;
import dev.repository.NatureRepository;

@Service
public class NatureService {

	private NatureRepository natureRepository;

	private MissionService missionService;

	public NatureService(NatureRepository natureRepository, MissionService missionService) {
		super();
		this.natureRepository = natureRepository;
		this.missionService = missionService;
	}

//liste Nature
	public List<Nature> getList() {
		return natureRepository.findAll();
	}

//id de nature
	public Optional<Nature> getById(Integer id) {
		return natureRepository.findById(id);
	}

//nature par nom	
	public Nature getParNom(String nom) {
		return natureRepository.findByNom(nom);
	}

//ajout en bdd

	@Transactional
	public Nature creer(String nom, Boolean missionFacturee, Boolean versementPrime, BigDecimal tjm,
			BigDecimal pourcentagePrime, BigDecimal plafond, Boolean plafondDepassable) {

		Nature nature = new Nature(nom, missionFacturee, versementPrime, tjm, pourcentagePrime, plafond,
				plafondDepassable, LocalDate.now());

		Nature natureEnregistree = this.natureRepository.save(nature);

		return natureEnregistree;
	}

//cette methode récupére l'id de nature utilisé par une mission si elle est utilisé dans mission
	// on conserve cette nature avec une date de la veille sinon elle recrée une
	// nouvelle nature de mission à la date du jour
	@Transactional
	public Nature update(Integer natureId, PatchNatureRequestDto newNature) {
		Nature oldNature = getById(natureId).get();
		if (!oldNature.getNom().equals(newNature.getNom() )) {
			if (getParNom(newNature.getNom()) != null) {
				throw new IllegalArgumentException("Nature avec le même nom existante");
			}
		}
		List<Mission> missions = missionService.getByNatureId(natureId);
		if (missions != null && !missions.isEmpty()) {
			LocalDate localDate = LocalDate.now().minusDays(1);
			oldNature.setFinValidite(localDate);
			natureRepository.save(oldNature);
			return creer(newNature.getNom(), newNature.isMissionFacturee(), newNature.isVersementPrime(),
					newNature.getTjm(), newNature.getPourcentagePrime(), newNature.getPlafond(),
					newNature.isPlafondDepassable());
		} else {
			oldNature.setNom(newNature.getNom());
			oldNature.setMissionFacturee(newNature.isMissionFacturee());
			oldNature.setVersementPrime(newNature.isVersementPrime());
			oldNature.setTjm(newNature.getTjm());
			oldNature.setPourcentagePrime(newNature.getPourcentagePrime());
			oldNature.setPlafond(newNature.getPlafond());
			oldNature.setPlafondDepassable(newNature.isPlafondDepassable());
			return natureRepository.save(oldNature);
		}

	}

	@Transactional
	public boolean deleteNature(Integer natureId) {
		Nature nature = getById(natureId).get();
		List<Mission> missions = missionService.getByNatureId(natureId);
		if (missions != null && !missions.isEmpty()) {
			nature.setFinValidite(LocalDate.now());
			natureRepository.save(nature);
			return false;

		} else {
			natureRepository.delete(nature);
			return true;
		}
	}

}

package dev.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.controller.nature.NatureRequestDto;
import dev.domain.Nature;
import dev.repository.NatureRepository;

@Service
public class NatureService {

	private NatureRepository natureRepository;

	public NatureService(NatureRepository natureRepository) {
		super();
		this.natureRepository = natureRepository;
	}

//liste Nature
	public List<Nature> getList() {
		return natureRepository.findAll();
	}

//id de nature
	public Optional<Nature> getById(Integer id) {
		return natureRepository.findById(id);
	}

//ajout en bdd

	@Transactional
	public Nature creerNature(NatureRequestDto dto) {
		Nature nature = new Nature();
		nature.setNom(dto.getNom());
		nature.setMissionFacturee(dto.isMissionFacturee());
		nature.setVersementPrime(dto.isVersementPrime());
		nature.setTjm(dto.getTjm());
		nature.setPourcentagePrime(dto.getPourcentagePrime());
		nature.setDebutValidite(dto.getDebutValidite());
		nature.setFinValidite(dto.getFinValidite());

		return this.natureRepository.save(nature);
	}

//modifier en bdd

	@Transactional
	public Nature updateNature(Integer idNature, String nouveauNom, boolean missionFacturee, boolean versementPrime,
			BigDecimal tjm, BigDecimal pourcentagePrime) {
		natureRepository.update(idNature, nouveauNom, missionFacturee, versementPrime, tjm, pourcentagePrime);
		return natureRepository.findById(idNature)
				.orElseThrow(() -> new RuntimeException("erreur : actualisation Nature"));

	}

// modifier en bdd

	@Transactional
	public String deleteNature(Integer id) {

		Optional<Nature> natureToRemove = this.getById(id);
		if (natureToRemove.isPresent()) {
			natureRepository.delete(natureToRemove.get());
			return "Suppression de la Nature" + id + " reussie";
		}
		return "id non trouvé";
	}

}

package dev.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.domain.Frais;
import dev.domain.Mission;
import dev.repository.FraisRepository;

@Service
public class FraisService {
	
	private FraisRepository fraisRepository;

	/**
	 * @param fraisRepo
	 */
	public FraisService(FraisRepository fraisRepository) {
		this.fraisRepository = fraisRepository;
	}
	
	// recupère la liste de toutes les notes de frais grâce au findAll
	public List<Frais> getList(){
		return fraisRepository.findAll();
	}
	
	
	public List<Frais> getListByMission(Integer idMission){
		return fraisRepository.FindAllByIdMission(idMission);
	}
	
	
	// création d'une nouvelle note de frais
	@Transactional
	public Frais creerFrais(LocalDate date, String natureFrais, BigDecimal montantFrais, Mission mission) {
		Frais frais = new Frais(date, natureFrais, montantFrais, mission);
		return fraisRepository.save(frais);
	}

	
	// modification d'une note de frais
	@Transactional
	public Frais updateFrais(Integer id, LocalDate date, String natureFrais, BigDecimal montantFrais) {
		fraisRepository.update(id, date, natureFrais, montantFrais);
		return fraisRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("erreur : actualisation Frais"));
	}
	
	// récupère une note de frais par son id
	@Transactional
	public Optional<Frais> getById(Integer id) {
		return fraisRepository.findById(id);
	}
	
	// supprime une note de frais
	@Transactional
	public String removeFrais(Integer id) {
		
		Optional<Frais> FraisToRemove=this.getById(id);
		if(FraisToRemove.isPresent()) {
			fraisRepository.delete(FraisToRemove.get());
			return "Suppression reussie";
		}
		return "id non trouvé";
	}
	
	


}

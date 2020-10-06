package dev.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.domain.Frais;
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
	
	// création d'une nouvelle note de frais
	@Transactional
	public Frais creerFrais(LocalDate date, String natureFrais, BigDecimal montantFrais) {
		Frais frais = new Frais(date, natureFrais, montantFrais);
		return fraisRepository.save(frais);
	}

}

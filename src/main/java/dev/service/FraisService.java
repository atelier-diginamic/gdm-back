package dev.service;

import java.util.List;

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

}

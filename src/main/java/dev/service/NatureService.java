package dev.service;


import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.controller.collegue.NatureRequestDto;
import dev.domain.Nature;

import dev.repository.NatureRepository;


@Service
public class NatureService {

	private NatureRepository natureRepository;

	
	public NatureService(NatureRepository natureRepository) {
		super();
		this.natureRepository = natureRepository;
	}

public List<Nature> getList(){
	return natureRepository.findAll();
}
	
//ajout en base
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


	}

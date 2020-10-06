package dev.service;

import java.util.List;
import java.util.Optional;

import dev.domain.Nature;
import dev.repository.NatureRepository;

public class NatureService {

	private NatureRepository natureRepository;

	
	public NatureService(NatureRepository natureRepository) {
		super();
		this.natureRepository = natureRepository;
	}



	public Optional <Nature> getNatureById (Integer id) {
		return natureRepository.findById(id);
}
	
	public List <Nature> listerNatures(){
		return natureRepository.findAll();
	}
	
}

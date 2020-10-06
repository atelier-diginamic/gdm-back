package dev.controller.frais;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Frais;
import dev.service.FraisService;

@CrossOrigin
@RestController
@RequestMapping("frais")
public class FraisController {
	
	private FraisService fraisService;
	
	/**
	 * @param fraisService
	 */
	public FraisController(FraisService fraisService) {
		this.fraisService = fraisService;
	}


	// affiche la liste de toutes les notes de frais
	@GetMapping
	public List<Frais> listeNotesDeFrais(){
		return fraisService.getList();
	}
	

}

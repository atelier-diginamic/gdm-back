package dev.controller.frais;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
//	@PostMapping
//	public ResponseEntity<?> newFrais(@RequestBody @Valid FraisRequestDto fraisRequestDto, BindingResult resValid) {
//
//		if (!resValid.hasErrors()) {
//			Frais frais = fraisService. //creerCollegue(colreq.getNom(), colreq.getPrenoms(),
//					colreq.getDateNaissance(), colreq.getEmail(), colreq.getPhotoUrl());
//
//			return ResponseEntity.ok(collegue);
//		} else {
//			return ResponseEntity.badRequest().body("tous les champs sont obligatoires !");
//		}
//
//	}
	

}

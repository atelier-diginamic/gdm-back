package dev.controller.frais;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	// affiche toutes les notes de frais pour une mission (en fonction de son id)
	@GetMapping("{idMission}")
	public List<Frais> listeNotesDeFraisParMission(@PathVariable Integer idMission) {
		return fraisService.getListByMission(idMission);

	}

	// création d'une nouvelle note de frais
	// ajouter les contraintes dans le front ?
	@PostMapping
	public ResponseEntity<?> newFrais(@RequestBody @Valid FraisRequestDto fraisRequestDto, BindingResult resValid) {

		if (!resValid.hasErrors()) {
			Frais frais = fraisService.creerFrais(fraisRequestDto.getDate(), fraisRequestDto.getNatureFrais(),
					fraisRequestDto.getMontant());
			return ResponseEntity.ok(frais);
		} else {
			return ResponseEntity.badRequest().body("Veuillez saisir des champs corrects");
		}

	}

	// méthode pour modifier une note de frais
	@PatchMapping("{id}")
	public ResponseEntity<?> editUser(@PathVariable Integer id, @RequestBody @Valid FraisRequestDto fraisDto,
			BindingResult resValid) {

		if (!resValid.hasErrors()) {
			// update des données en base
			Frais editFrais = fraisService.updateFrais(id, fraisDto.getDate(), fraisDto.getNatureFrais(),
					fraisDto.getMontant());

			// réponse renvoyée : toutes les données sauf l'id
			FraisResponsePatch editFraisResponse = new FraisResponsePatch();
			editFraisResponse.setDate(editFrais.getDate());
			editFraisResponse.setNatureFrais(editFrais.getNatureFrais());
			editFraisResponse.setMontantFrais(editFrais.getMontantFrais());
			return ResponseEntity.ok(editFraisResponse);

		} else {
			return ResponseEntity.badRequest().body("Veuillez saisir des champs corrects");
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> supprimerFrais(@PathVariable Integer id) {
		return ResponseEntity.ok(fraisService.removeFrais(id));

	}

}

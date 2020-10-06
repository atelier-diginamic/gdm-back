package dev.controller.frais;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Frais;
import dev.domain.Mission;
import dev.repository.MissionRepository;
import dev.service.FraisService;


@CrossOrigin
@RestController
@RequestMapping("frais")
public class FraisController {

	private FraisService fraisService;
	private MissionRepository missionRepo;
	

	/**
	 * @param fraisService
	 */
	public FraisController(FraisService fraisService, MissionRepository missionRepo) {
		this.fraisService = fraisService;
		this.missionRepo = missionRepo;
	}
	

	// affiche toutes les notes de frais pour une mission (en fonction de son id)
	@GetMapping("{idMission}")
	public ResponseEntity<?> listeNotesDeFraisParMission(@PathVariable Integer idMission) {
		Optional<Mission> byId = missionRepo.findById(idMission);

		if(byId.isPresent()) {
			return ResponseEntity.ok(byId.get().getFrais());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	

	// création d'une nouvelle note de frais
	// ajouter les contraintes dans le front ?
	@PostMapping
	public ResponseEntity<?> newFrais(@RequestBody @Valid FraisRequestDto fraisRequestDto, BindingResult resValid) {

		if (!resValid.hasErrors()) {
			Frais frais = fraisService.creerFrais(fraisRequestDto.getDate(), fraisRequestDto.getNatureFrais(), fraisRequestDto.getMontant());
			return ResponseEntity.ok(frais);
		} else {
			return ResponseEntity.badRequest().body("Veuillez saisir des champs corrects");
		}

	}
	


}

package dev.controller.frais;

import java.util.ArrayList;
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
import dev.domain.Mission;
import dev.repository.MissionRepository;
import dev.service.FraisService;

@CrossOrigin
@RestController
@RequestMapping("frais")
public class FraisController {

	private FraisService fraisService;
	private MissionRepository missionRepository;

	/**
	 * @param fraisService
	 */
	public FraisController(FraisService fraisService, MissionRepository missionRepository) {
		this.fraisService = fraisService;
		this.missionRepository = missionRepository;

	}

	// affiche toutes les notes de frais pour une mission (en fonction de son id)

	@GetMapping("{idMission}")
	public List<FraisResponseDto> listeNotesDeFraisParMission(@PathVariable Integer idMission) {
		
		List<FraisResponseDto> listResponse = new ArrayList<>();
		
		for(Frais f : fraisService.getListByMission(idMission)) {
			listResponse.add(new FraisResponseDto(f));
		}
		return listResponse;

	}
	

	// création d'une nouvelle note de frais
	
	@PostMapping("{idMission}")
	public ResponseEntity<?> newFrais(@PathVariable Integer idMission, @RequestBody @Valid FraisRequestDto fraisRequestDto, BindingResult resValid) {
		
		if (!resValid.hasErrors()) {
			Mission mission = missionRepository.findById(idMission)
					.orElseThrow(() -> new RuntimeException("erreur : cette id ne correspond à aucune mission"));
			
			Frais frais = fraisService.creerFrais(fraisRequestDto.getDate(), fraisRequestDto.getNatureFrais(),
					fraisRequestDto.getMontantFrais(), mission);
			FraisResponseDto fraisResponse = new FraisResponseDto(frais);
			return ResponseEntity.ok(fraisResponse);
		} else {
			return ResponseEntity.badRequest().body("Veuillez saisir des champs corrects");
		}

	}
	
	
	

	// méthode pour modifier une note de frais
	@PatchMapping("{id}")
	public ResponseEntity<?> editFrais(@PathVariable Integer id, @RequestBody @Valid FraisRequestDto fraisDto,
			BindingResult resValid) {

		if (!resValid.hasErrors()) {
			// update des données en base
			Frais editFrais = fraisService.updateFrais(id, fraisDto.getDate(), fraisDto.getNatureFrais(),
					fraisDto.getMontantFrais());

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

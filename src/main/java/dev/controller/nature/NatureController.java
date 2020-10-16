package dev.controller.nature;

import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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
import dev.domain.Nature;
import dev.service.NatureService;

@CrossOrigin
@RestController
@RequestMapping("natures")
public class NatureController {
	private NatureService natureService;

	public NatureController(NatureService natureService) {
		super();
		this.natureService = natureService;

	}

//affiche la liste de toutes les natures

	@GetMapping
	public List<Nature> listNature() {
		return natureService.getList();
	}

//affiche la nature par son Id	
	@GetMapping("{id}")
	public Nature getNatureId(@PathVariable Integer id) {
		return natureService.getById(id).get();
	}

//ajouter une nature si elle est existante 
	@PostMapping
	public ResponseEntity<?> createNewNature(@RequestBody @Valid NatureRequestDto dto) {

		Nature existing = natureService.getParNom(dto.getNom());

		if (existing != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Nature existante ! veuillez saisir une nouvelle nature");
		}

		Nature natureCree = natureService.creer(dto.getNom(), dto.isMissionFacturee(), dto.isVersementPrime(),
				dto.getTjm(), dto.getPourcentagePrime(), dto.getPlafond(), dto.isPlafondDepassable());
		return ResponseEntity.status(HttpStatus.OK).body(natureCree);

	}

//modifier une nature 

	@PatchMapping("/{idNature}")
	public Nature uptadeNature(@PathVariable Integer idNature, @RequestBody @Valid PatchNatureRequestDto dto,
			BindingResult resValid) {
		return natureService.update(idNature, dto);
	}

	// supprimer une nature

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNature(@PathVariable Integer id) {
		boolean supp = natureService.deleteNature(id);
		if (supp) {
			return ResponseEntity.status(HttpStatus.OK).body("Nature supprimée");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("date de fin de validité mise à jour");
		}

	}
}

package dev.controller.nature;

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

import dev.domain.Nature;
import dev.service.NatureService;

@CrossOrigin
@RestController
@RequestMapping("natures")
public class NatureController {
	private NatureService natureService;

	public NatureController(NatureService natureService) {

		this.natureService = natureService;
	}

//affiche la liste de  toutes les natures

	@GetMapping
	public List<Nature> listNature() {
		return natureService.getList();
	}

	
//ajouter une nature 
	
	@PostMapping
	public NatureResponseDto createNewNature(@RequestBody @Valid NatureRequestDto dto) {
		Nature natureCree = this.natureService.creerNature(dto);
		return new NatureResponseDto(natureCree.getNom());
	}

//modifier une nature 
	
	@PatchMapping("/{idNature}")
	public ResponseEntity<?> uptadeNature(@PathVariable Integer idNature, @RequestBody @Valid PatchNatureRequestDto dto,
			BindingResult resValid) {
		Nature editNature = natureService.updateNature(idNature, dto.getNom(), dto.isMissionFacturee(),
				dto.isVersementPrime(), dto.getTjm(), dto.getPourcentagePrime());
		return ResponseEntity.ok(editNature);
	}

//supprimer une nature	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNature(@PathVariable Integer id) {
		return ResponseEntity.ok(natureService.deleteNature(id));

	}

}

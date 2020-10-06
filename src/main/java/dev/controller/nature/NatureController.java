package dev.controller.nature;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.collegue.NatureRequestDto;
import dev.domain.Nature;
import dev.repository.NatureRepository;
import dev.utils.ValidateDate;


@RestController
@RequestMapping("natures")
public class NatureController {

	@Autowired
private NatureRepository natureRepository;



//cherche toutes les natures

@GetMapping 
public ResponseEntity<?> searchAll() {
	return ResponseEntity.ok(this.natureRepository.findAll());
}




//cherche les natures par nom

@GetMapping("/{nom}")
public ResponseEntity<?> search (@PathVariable String nom){
	Optional <Nature> nature = natureRepository.findByNom(nom);
	
	if (nature.isPresent()) {
		return ResponseEntity.ok(nature.get());
		
	}else {
		return ResponseEntity.badRequest().body("Aucune nature trouvée pour le nom "+ nom);
	}
}


@PostMapping
public ResponseEntity<?> ajoutNature(@RequestBody @Valid NatureRequestDto dto,BindingResult resultatValidation  ){
	Nature nature = new Nature ();
	
	
	//Vérification de la date 
	if (ValidateDate.validateInputDate(dto.getDateFin())) {
		nature.setFinValidite(LocalDate.parse(dto.getDateFin()));
	}else {
		nature.setFinValidite(LocalDate.MAX);
	}
	return null;
	
	
	
}




	
}

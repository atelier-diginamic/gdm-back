package dev.controller.nature;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.collegue.NatureRequestDto;
import dev.controller.collegue.NatureResponseDto;
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
public List<Nature> listNature(){
	return natureService.getList();
}

@PostMapping
public NatureResponseDto createNewNature(@RequestBody @Valid NatureRequestDto dto) {
	Nature natureCree = this.natureService.creerNature(dto);
	
	return new NatureResponseDto(natureCree.getNom());
}

	
}

package dev.controller.mission;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Collegue;
import dev.repository.CollegueRepo;
import dev.service.MissionService;

@RestController
@CrossOrigin
@RequestMapping("missions")
public class MissionController {

	private MissionService missionService;
	private CollegueRepo collegueRepository;

	public MissionController(MissionService missionService, CollegueRepo collegueRepository) {
		this.missionService = missionService;
		this.collegueRepository = collegueRepository;

	}

	@GetMapping("{idCollegue}")
	public ResponseEntity<?> listeMissions(@PathVariable Long idCollegue) {

		Optional<Collegue> collegueOp = collegueRepository.findById(idCollegue);

		if (collegueOp.isPresent()) {
			return ResponseEntity.ok(collegueOp.get().getMissions());
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}

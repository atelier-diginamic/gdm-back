package dev.controller.mission;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Mission;
import dev.service.MissionService;

@RestController
@CrossOrigin
@RequestMapping("missions")
public class MissionController {

	private MissionService missionService;

	public MissionController(MissionService missionService) {
		this.missionService = missionService;
	}

	@GetMapping
	public List<Mission> listeNotesDeFrais() {
		return missionService.getList();
	}

}

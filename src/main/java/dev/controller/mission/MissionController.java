package dev.controller.mission;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Collegue;
import dev.domain.Mission;
import dev.repository.CollegueRepo;
import dev.repository.NatureRepository;
import dev.service.MissionService;

@RestController
@CrossOrigin
@RequestMapping("missions")
public class MissionController {

	private MissionService missionService;
	private NatureRepository natureRepositorie;
	private CollegueRepo collegueRepository;

	public MissionController(MissionService missionService, NatureRepository natureRepositorie,
			CollegueRepo collegueRepository) {
		this.missionService = missionService;
		this.natureRepositorie = natureRepositorie;
		this.collegueRepository = collegueRepository;

	}

	@GetMapping("{idCollegue}")
	public List<MissionReponseDto> listeMissions(@PathVariable Long idCollegue) {

		List<MissionReponseDto> listReponse = new ArrayList<>();

		for (Mission m : missionService.listMissions(idCollegue)) {
			listReponse.add(new MissionReponseDto(m));
		}

		return listReponse;

	}

	@PostMapping("{idCollegue}")
	public MissionReponseDto demandeMission(@PathVariable Long idCollegue,
			@RequestBody MissionRequestDto missionRequestDto) {

		Mission mission = new Mission();

		Collegue collegue = collegueRepository.findById(idCollegue)
				.orElseThrow(() -> new RuntimeException("erreur : cette id ne corresponde pas à aucune mission"));

		mission.setDateDebut(missionRequestDto.getDateDebut());
		mission.setDateFin(missionRequestDto.getDateFin());
		mission.setVilleDepart(missionRequestDto.getVilleDepart());
		mission.setVilleArrivee(missionRequestDto.getVilleArrivee());
		mission.setTransport(missionRequestDto.getTransport());
		mission.setNature(natureRepositorie.findByNom(missionRequestDto.getNomNature()));
		mission.setCollegue(collegue);

		return new MissionReponseDto(missionService.creerMission(mission));

	}

}

package dev.controller.mission;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Collegue;
import dev.domain.Mission;
import dev.domain.Statut;
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
	public ResponseEntity<?> demandeMission(@PathVariable Long idCollegue,
			@RequestBody @Valid MissionRequestDto missionRequestDto, BindingResult resValid) {

		if (!resValid.hasErrors()) {
			Mission mission = new Mission();

			Collegue collegue = collegueRepository.findById(idCollegue)
					.orElseThrow(() -> new RuntimeException("erreur : cette id ne corresponde pas à aucune mission"));

			mission.setDateDebut(missionRequestDto.getDateDebut());
			mission.setDateFin(missionRequestDto.getDateFin());
			mission.setVilleDepart(missionRequestDto.getVilleDepart());
			mission.setVilleArrivee(missionRequestDto.getVilleArrivee());
			mission.setTransport(missionRequestDto.getTransport());
			mission.setNature(natureRepositorie.findByNom(missionRequestDto.getNomNature()));
			mission.setStatut(Statut.INITIALE);
			mission.setCollegue(collegue);
			mission.setPrime(BigDecimal.ZERO);

			return ResponseEntity.ok(new MissionReponseDto(missionService.creerMission(mission)));
		} else {
			return ResponseEntity.badRequest().body("Veuillez verifier les dates");
		}

	}

	@PatchMapping("{id}")
	public MissionReponseDto editMission(@PathVariable Integer id, @RequestBody MissionRequestDto missionRequestDto) {

		Mission mission = missionService.updateCollegue(id, missionRequestDto.getDateDebut(),
				missionRequestDto.getDateFin(), missionRequestDto.getVilleDepart(), missionRequestDto.getVilleArrivee(),
				missionRequestDto.getTransport(), natureRepositorie.findByNom(missionRequestDto.getNomNature()),
				Statut.INITIALE);

		;
		return new MissionReponseDto(mission);

	}

	@PatchMapping("nuit")
	public void traitementNuit() throws Exception {
		missionService.traitementNuit();

	}

	@GetMapping("manager/{idManager}")
	public List<MissionReponseDto> listeMissionsManager(@PathVariable Long idManager) {

		List<MissionReponseDto> listReponse = new ArrayList<>();

		for (Mission m : missionService.listMissionsManager(idManager)) {
			listReponse.add(new MissionReponseDto(m));
		}
		return listReponse;
	}

	@PatchMapping("manager/{idManager}")
	public List<MissionReponseDto> aceptationMission(@RequestBody MissionPatchDto missionPatchDto,
			@PathVariable Long idManager) {
		missionService.acceptationMission(missionPatchDto.getId(), missionPatchDto.isValide());

		List<MissionReponseDto> listReponse = new ArrayList<>();

		for (Mission m : missionService.listMissionsManager(idManager)) {
			listReponse.add(new MissionReponseDto(m));
		}
		return listReponse;
	}

	@GetMapping
	public ResponseEntity<?> getMission(@RequestParam Integer id) {

		Optional<Mission> findById = missionService.getMission(id);
		if (findById.isPresent()) {
			MissionResponseGetMissionDto missionReponse = new MissionResponseGetMissionDto(findById.get());
			return ResponseEntity.ok(missionReponse);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> effacerNotes(@PathVariable Integer id) {

		List<Mission> isRemoved = missionService.delateMission(id);

		if (isRemoved.isEmpty()) {
			return new ResponseEntity<>("Acune note ne corresponde pas à l'id : " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(isRemoved, HttpStatus.OK);

	}

}

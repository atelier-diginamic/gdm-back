package dev.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.Statut;
import dev.repository.MissionRepository;

@Service
public class MissionService {

	private MissionRepository missionRepossitory;

	public MissionService(MissionRepository missionRepossitory) {
		this.missionRepossitory = missionRepossitory;
	}

	public List<Mission> getList() {

		return missionRepossitory.findAll();
	}

	public Mission creerMission(Mission mission) {

		return missionRepossitory.save(mission);
	}

	public List<Mission> listMissions(Long idCollegue) {
		return missionRepossitory.findAllByIdCollegue(idCollegue);

	}

	@Transactional
	public Mission updateCollegue(Integer id, LocalDate dateDebut, LocalDate dateFin, String villeDepart,
			String villeArrivee, String transport, Nature findByNom, Statut statut) {

		missionRepossitory.update(id, dateDebut, dateFin, villeDepart, villeArrivee, transport, findByNom, statut);

		return missionRepossitory.findById(id)
				.orElseThrow(() -> new RuntimeException("erreur : actualisation Mission"));
	}

}

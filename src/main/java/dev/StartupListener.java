package dev;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.domain.Collegue;
import dev.domain.Mission;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.Version;
import dev.repository.CollegueRepo;
import dev.repository.MissionRepository;
import dev.repository.VersionRepo;
import dev.service.MissionService;

/**
 * Code de démarrage de l'application. Insertion de jeux de données.
 */
@Component
public class StartupListener {

	private String appVersion;
	private VersionRepo versionRepo;
	private PasswordEncoder passwordEncoder;
	private CollegueRepo collegueRepo;
	private MissionRepository missionRep;
	private MissionService missionServ;

	public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo,
			PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, MissionService missionServ,
			MissionRepository missionRep) {
		this.appVersion = appVersion;
		this.versionRepo = versionRepo;
		this.passwordEncoder = passwordEncoder;
		this.collegueRepo = collegueRepo;
		this.missionServ = missionServ;
		this.missionRep = missionRep;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {
		this.versionRepo.save(new Version(appVersion));

		// Création de deux utilisateurs

		Collegue col1 = new Collegue();
		col1.setNom("Admin");
		col1.setPrenom("DEV");
		col1.setEmail("admin@dev.fr");
		col1.setMotDePasse(passwordEncoder.encode("superpass"));
		col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR),
				new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col1);

		Collegue col2 = new Collegue();
		col2.setNom("User");
		col2.setPrenom("DEV");
		col2.setEmail("user@dev.fr");
		col2.setMotDePasse(passwordEncoder.encode("superpass"));
		col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col2);

		Collegue col3 = new Collegue();
		col3.setNom("Manag");
		col3.setPrenom("DEV");
		col3.setEmail("manag@dev.fr");
		col3.setMotDePasse(passwordEncoder.encode("superpass"));
		col3.setRoles(Arrays.asList(new RoleCollegue(col3, Role.ROLE_MANAGER),
				new RoleCollegue(col3, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col3);

		Collegue col4 = new Collegue();
		col4.setNom("Amine");
		col4.setPrenom("DEV");
		col4.setEmail("amine@dev.fr");
		col4.setMotDePasse(passwordEncoder.encode("superpass"));
		col4.setRoles(Arrays.asList(new RoleCollegue(col4, Role.ROLE_UTILISATEUR)));
		col4.setManager(col3);
		this.collegueRepo.save(col4);

		Collegue col5 = new Collegue();
		col5.setNom("Louise");
		col5.setPrenom("DEV");
		col5.setEmail("louise@dev.fr");
		col5.setMotDePasse(passwordEncoder.encode("superpass"));
		col5.setRoles(Arrays.asList(new RoleCollegue(col5, Role.ROLE_UTILISATEUR)));
		col5.setManager(col3);
		this.collegueRepo.save(col5);

		Collegue col6 = new Collegue();
		col6.setNom("Helvin");
		col6.setPrenom("DEV");
		col6.setEmail("helvin@dev.fr");
		col6.setMotDePasse(passwordEncoder.encode("superpass"));
		col6.setRoles(Arrays.asList(new RoleCollegue(col6, Role.ROLE_UTILISATEUR)));
		col6.setManager(col3);
		this.collegueRepo.save(col6);

		for (Mission m : missionRep.findAll()) {
			missionServ.updateMission(m.getId(), col6);
		}
		;

	}

}

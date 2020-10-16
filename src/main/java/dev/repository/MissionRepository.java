package dev.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.Statut;

public interface MissionRepository extends JpaRepository<Mission, Integer> {

	@Query("select m from Mission m join fetch m.collegue c where c.id = ?1")
	List<Mission> findAllByIdCollegue(Long idCollegue);

	@Modifying
	@Query("update Mission m set m.dateDebut =?2, m.dateFin=?3, m.villeDepart=?4, m.villeArrivee=?5, m.transport=?6, m.nature=?7, m.statut=?8 where m.id=?1")
	void update(Integer id, LocalDate dateDebut, LocalDate dateFin, String villeDepart, String villeArrivee,
			String transport, Nature nature, Statut Statut);

	List<Mission> findAllByStatut(Statut initiale);

	@Modifying
	@Query("update Mission m set m.statut=?2 where m.id=?1")
	void updateStatut(int id, Statut statut);

	@Modifying
	@Query("update Mission m set m.prime=?2 where m.id=?1")
	void updatePrime(int id, BigDecimal prime);

	@Query("select m from Mission m join fetch m.collegue  c inner join  c.manager a where a.id = ?1 and m.statut=?2	")
	List<Mission> findAllByIdManager(Long idManager, Statut enAttenteValidation);
	
	
	List<Mission> findByNatureId (Integer idNature);

}

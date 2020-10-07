package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Integer> {

	@Query("select m from Mission m join fetch m.collegue c where c.id = ?1")
	List<Mission> findAllByIdCollegue(Long idCollegue);

}

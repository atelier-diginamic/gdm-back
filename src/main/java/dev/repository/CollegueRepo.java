package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Collegue;

public interface CollegueRepo extends JpaRepository<Collegue, Long> {

	Optional<Collegue> findByEmail(String email);

	@Modifying
	@Query("update Collegue c set c.motDePasse = ?2 where c.id=?1")
	void update(Long id, String encode);
}

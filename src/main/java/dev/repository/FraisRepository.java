package dev.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Frais;

public interface FraisRepository extends JpaRepository<Frais, Integer>{
	
	// modification d'une note de frais
	@Modifying
	@Query("update Frais f set f.date = ?2, f.natureFrais =?3, f.montantFrais =?4 where f.id=?1")
	void update(Integer id, LocalDate date, String natureFrais, BigDecimal montantFrais);

}

package dev.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Nature;

public interface NatureRepository extends JpaRepository<Nature, Integer> {

	Nature findByNom(String nom);

	@Modifying
	@Query("update Nature n set n.nom=?2 ,n.missionFacturee=?3,n.versementPrime=?4,n.tjm=?5,n.pourcentagePrime=?6 where n.id=?1")
	void update(Integer idNature, String nouveauNom, boolean missionFacturee, boolean versementPrime, BigDecimal tjm,
			BigDecimal pourcentagePrime);

}

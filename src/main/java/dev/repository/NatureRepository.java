package dev.repository;




import org.springframework.data.jpa.repository.JpaRepository;


import dev.domain.Nature;

public interface NatureRepository extends JpaRepository<Nature, Integer> {


	
}

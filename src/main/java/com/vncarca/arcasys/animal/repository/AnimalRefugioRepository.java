package com.vncarca.arcasys.animal.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vncarca.arcasys.animal.model.AnimalRefugio;

@Repository
public interface AnimalRefugioRepository
		extends JpaRepository<AnimalRefugio, Long>, JpaSpecificationExecutor<AnimalRefugio> {

	@Query(value = "Select * from animales_refugio where animales_refugio.adoptado = :adoptado", countQuery = "select count(*) from animales_refugio", nativeQuery = true)
	Page<AnimalRefugio> getAnimalesPorestadoAdopcion(boolean adoptado, Pageable pageable);

	Page<AnimalRefugio> findByDeleted(Pageable pageable, Boolean deleted);

	@Transactional()
	@Modifying(clearAutomatically = true)
	@Query("update AnimalRefugio a set a.deleted = ?1 where a.id = ?2")
	void restoreAnimal(Boolean isDeleted, Long id);

	@Transactional()
	@Modifying(clearAutomatically = true)
	@Query("update AnimalRefugio  a set a.adoptado = false, a.lugarEstancia ='REFUGIO' where a.id in (select animal from Adopcion )")
	void restoreAdopcion();

}

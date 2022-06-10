package com.vncarca.arcasys.animal.repository;

import com.vncarca.arcasys.animal.model.Animal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>, JpaSpecificationExecutor<Animal> {

	// Page<Animal> getFichaClina();

	// Animal getAllOfAnimal(Pageable pageable);

}

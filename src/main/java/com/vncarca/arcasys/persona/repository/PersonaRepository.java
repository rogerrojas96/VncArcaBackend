package com.vncarca.arcasys.persona.repository;

import com.vncarca.arcasys.persona.model.Persona;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {
	public Page<Persona> findAll(Pageable pageable);
	
}

package com.vncarca.arcasys.veterinario.repository;

import com.vncarca.arcasys.veterinario.model.Veterinario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario,Long> {
	public Page<Veterinario> findAll(Pageable pageable);
	
}

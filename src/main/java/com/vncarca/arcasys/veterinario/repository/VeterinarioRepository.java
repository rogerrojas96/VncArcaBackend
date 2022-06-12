package com.vncarca.arcasys.veterinario.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vncarca.arcasys.veterinario.model.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
	public Page<Veterinario> findByPersona_cedulaIn(Pageable pageable, Collection<String> cedula);

}

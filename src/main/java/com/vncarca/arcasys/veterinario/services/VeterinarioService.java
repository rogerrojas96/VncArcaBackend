package com.vncarca.arcasys.veterinario.services;

import java.util.Collection;
import java.util.List;

import com.vncarca.arcasys.veterinario.model.Veterinario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VeterinarioService {
	public Page<Veterinario> findAll(Pageable pageable);

	public Page<Veterinario> findByCedula(Pageable pageable, Collection<String> cedula);

	public List<Veterinario> findAll();

	public Veterinario save(Veterinario veterinario);

	public Veterinario findById(Long id);

	public void delete(Long id);
}

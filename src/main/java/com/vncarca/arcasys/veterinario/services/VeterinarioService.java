package com.vncarca.arcasys.veterinario.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.globalService.GlovalService;
import com.vncarca.arcasys.veterinario.model.Veterinario;
import com.vncarca.arcasys.veterinario.model.VeterinarioDTO;

public interface VeterinarioService extends GlovalService<VeterinarioDTO, Veterinario> {
	public Page<Veterinario> findAll(Pageable pageable);

	public Page<Veterinario> findByCedula(Pageable pageable, String cedula);

	public Page<Veterinario> findByCedulaLike(Pageable pageable, String cedula);

	public List<Veterinario> findAll();

	public Veterinario save(Veterinario veterinario);

	public Veterinario findById(Long id);

	public Veterinario findByPersonaId(Long id);

	public void delete(Long id);
}

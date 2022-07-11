package com.vncarca.arcasys.veterinario.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.globalService.GlobalService;
import com.vncarca.arcasys.veterinario.model.Veterinario;
import com.vncarca.arcasys.veterinario.model.VeterinarioDTO;

public interface VeterinarioService extends GlobalService<VeterinarioDTO, Veterinario> {
	public Page<VeterinarioDTO> findAll(Pageable pageable);

	public Page<VeterinarioDTO> findByCedula(Pageable pageable, String cedula);

	public Page<VeterinarioDTO> findByCedulaLike(Pageable pageable, String cedula);

	public List<VeterinarioDTO> findAll();

	public VeterinarioDTO save(VeterinarioDTO veterinario);

	public VeterinarioDTO findById(Long id);

	public Veterinario findByPersonaId(Long id);

	public void delete(Long id);
}

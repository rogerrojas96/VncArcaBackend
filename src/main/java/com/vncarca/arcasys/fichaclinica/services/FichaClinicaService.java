package com.vncarca.arcasys.fichaclinica.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.fichaclinica.model.FichaClinica;
import com.vncarca.arcasys.fichaclinica.model.FichaClinicaDTO;

public interface FichaClinicaService {
	public Page<FichaClinica> findAll(Pageable pageable);

	public Page<FichaClinica> findBytipoPacienteContaining(Pageable pageable, String tipoPaciente);

	public FichaClinica save(FichaClinica fichaClinica);

	// public List<FichaClinicaDTO> findAllDTO();
	public List<FichaClinica> findAll();

	public List<FichaClinicaDTO> findByanimalId(Long id);

	public FichaClinica findById(Long id);

	public void delete(Long id);

	public FichaClinicaDTO fichaClinicaToDTO(FichaClinica ficjaClinica);

}

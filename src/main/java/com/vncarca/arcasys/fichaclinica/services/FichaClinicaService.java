package com.vncarca.arcasys.fichaclinica.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.fichaclinica.model.FichaClinica;
import com.vncarca.arcasys.fichaclinica.model.FichaClinicaDTO;
import com.vncarca.arcasys.fichaclinica.model.FichaClinicaRequestDTO;
import com.vncarca.arcasys.globalService.GlobalService;

public interface FichaClinicaService extends GlobalService<FichaClinicaDTO, FichaClinica> {
	Page<FichaClinicaDTO> findAll(Pageable pageable);

	Page<FichaClinicaDTO> findBytipoPacienteContaining(Pageable pageable, String tipoPaciente);

	FichaClinicaDTO save(FichaClinicaRequestDTO fichaClinicaRequestDTO);

	FichaClinicaDTO update(FichaClinicaRequestDTO fichaClinica);

	// List<FichaClinicaDTO> findAllDTO();
	List<FichaClinicaDTO> findAll();

	List<FichaClinicaDTO> findByanimalId(Long id);

	FichaClinicaDTO findById(Long id);

	void delete(Long id);

	FichaClinica convertRequestToEntity(FichaClinicaRequestDTO fichaClinicaRequestDTO);
}

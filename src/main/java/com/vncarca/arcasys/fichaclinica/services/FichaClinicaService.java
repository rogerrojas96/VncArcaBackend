package com.vncarca.arcasys.fichaclinica.services;

import java.util.List;

import com.vncarca.arcasys.fichaclinica.model.FichaClinica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FichaClinicaService {
	public Page<FichaClinica> findAll(Pageable pageable);
	public FichaClinica save(FichaClinica fichaClinica);
	public List<FichaClinica> findAll();
	public FichaClinica findById(Long id);
	public void delete(Long id);

}

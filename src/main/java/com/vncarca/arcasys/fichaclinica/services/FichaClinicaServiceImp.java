package com.vncarca.arcasys.fichaclinica.services;

import java.util.List;

import com.vncarca.arcasys.fichaclinica.model.FichaClinica;
import com.vncarca.arcasys.fichaclinica.repository.FichaClinicaRepository;
import com.vncarca.arcasys.tratamiento.model.Tratamiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FichaClinicaServiceImp implements FichaClinicaService{
@Autowired
FichaClinicaRepository fichaClinicaRepository;
	@Override
	public Page<FichaClinica> findAll(Pageable pageable) {
		
		return fichaClinicaRepository.findAll(pageable);
	}

	@Override
	public FichaClinica save(FichaClinica fichaClinica) {
		
		return fichaClinicaRepository.save(fichaClinica);
	}

	@Override
	public List<FichaClinica> findAll() {
		
		return fichaClinicaRepository.findAll();
	}

	@Override
	public FichaClinica findById(Long id) {
		
		return fichaClinicaRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		
		fichaClinicaRepository.deleteById(id);
	}
	
	
}

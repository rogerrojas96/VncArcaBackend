package com.vncarca.arcasys.tratamiento.services;

import java.util.List;


import com.vncarca.arcasys.tratamiento.model.Tratamiento;
import com.vncarca.arcasys.tratamiento.repository.TratamientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TratamientoServiceImp implements TratamientoService{
    @Autowired
    TratamientoRepository tratamientoRepository;
	@Override
	public Page<Tratamiento> findAll(Pageable pageable) {
		
		return tratamientoRepository.findAll(pageable);
	}

	@Override
	public Tratamiento save(Tratamiento tratamiento) {
		
		return tratamientoRepository.save(tratamiento);
	}

	@Override
	public List<Tratamiento> findAll() {
		
		return tratamientoRepository.findAll();
	}

	@Override
	public Tratamiento findById(Long id) {
		
		return tratamientoRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		
		tratamientoRepository.deleteById(id);
	}
	
}

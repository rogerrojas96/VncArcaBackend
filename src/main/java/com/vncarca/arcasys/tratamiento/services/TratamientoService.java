package com.vncarca.arcasys.tratamiento.services;

import java.util.List;


import com.vncarca.arcasys.tratamiento.model.Tratamiento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TratamientoService {
    public Page<Tratamiento> findAll(Pageable pageable);
	public Tratamiento save(Tratamiento tratamiento);
	public List<Tratamiento> findAll();
	public Tratamiento findById(Long id);
	public void delete(Long id);

    
}

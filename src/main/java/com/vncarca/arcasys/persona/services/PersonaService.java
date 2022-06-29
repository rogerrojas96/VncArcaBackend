package com.vncarca.arcasys.persona.services;

import java.util.List;

import com.vncarca.arcasys.globalService.GlobalService;
import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.model.PersonaDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonaService extends GlobalService<PersonaDto, Persona> {
	public Page<Persona> findAll(Pageable pageable);

	public Page<Persona> findByCedula(Pageable pageable, String cedula);

	public List<Persona> findAll();

	public Persona save(Persona persona);

	public Persona findById(Long id);

	public void delete(Long id);
}

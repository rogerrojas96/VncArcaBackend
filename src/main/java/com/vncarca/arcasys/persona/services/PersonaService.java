package com.vncarca.arcasys.persona.services;

import java.util.List;

import com.vncarca.arcasys.globalService.GlobalService;
import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.model.PersonaDto;

import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonaService extends GlobalService<PersonaDtoExtends, Persona> {
	public Page<PersonaDtoExtends> findAll(Pageable pageable);

	public Page<PersonaDtoExtends> findByCedula(Pageable pageable, String cedula);

	public List<PersonaDtoExtends> findAll();

	public PersonaDtoExtends save(PersonaDtoExtends persona);

	public PersonaDtoExtends findById(Long id);

	public void delete(Long id);
}

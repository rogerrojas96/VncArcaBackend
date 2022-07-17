package com.vncarca.arcasys.persona.services;

import com.vncarca.arcasys.globalService.GlobalService;
import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonaService extends GlobalService<PersonaDtoExtends, Persona> {
	Page<PersonaDtoExtends> findAll(Pageable pageable);

	Page<PersonaDtoExtends> findByCedula(Pageable pageable, String cedula);

	PersonaDtoExtends findByCedula(String cedula);

	PersonaDtoExtends findByCedulaNotClientes(String cedula);

	List<PersonaDtoExtends> findAll();

	PersonaDtoExtends save(PersonaDtoExtends persona);

	PersonaDtoExtends findById(Long id);

	void delete(Long id);
}
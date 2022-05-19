package com.vncarca.arcasys.persona.services;

import java.util.List;

import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.repository.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImp implements PersonaService {
	@Autowired
	PersonaRepository personaRepository;

	@Override
	public Page<Persona> findAll(Pageable pageable) {

		return personaRepository.findAll(pageable);
	}

	@Override
	public List<Persona> findAll() {

		return personaRepository.findAll();
	}

	@Override
	public Persona save(Persona persona) {

		return personaRepository.save(persona);
	}

	@Override
	public Persona findById(Long id) {

		return personaRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		personaRepository.deleteById(id);
	}
}

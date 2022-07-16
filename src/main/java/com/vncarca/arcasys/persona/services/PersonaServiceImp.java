package com.vncarca.arcasys.persona.services;

import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import com.vncarca.arcasys.persona.repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonaServiceImp implements PersonaService {
	@Autowired
	PersonaRepository personaRepository;
  @Autowired
	ModelMapper modelMapper;
	@Override
	public Page<PersonaDtoExtends> findAll(Pageable pageable) {

		return personaRepository.findAll(pageable).map(this::convertToDto);
	}

	@Override
	public List<PersonaDtoExtends> findAll() {
		return personaRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public PersonaDtoExtends save(PersonaDtoExtends persona) {
		return convertToDto(personaRepository.save(convertToEntity(persona)));
	}

	@Override
	public PersonaDtoExtends findById(Long id) {
		return personaRepository.findById(id).map(this::convertToDto).orElseThrow(() -> new NoSuchElementException(
				"Persona con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public void delete(Long id) {
		if(Objects.equals(findById(id).getId(), id))
			personaRepository.deleteById(id);
	}

	@Override
	public Page<PersonaDtoExtends> findByCedula(Pageable pageable, String cedula) {
		return personaRepository.findByCedula(pageable, cedula).map(this::convertToDto);
	}

	@Override
	public PersonaDtoExtends convertToDto(Persona e) {
		return new PersonaDtoExtends(e.getCedula(), e.getNombre(), e.getApellidos(), e.getDireccion(), e.getTelefono(), e.getCelular(), e.getCorreo(), e.getId());
	}

	@Override
	public Persona convertToEntity(PersonaDtoExtends dto) {
		return modelMapper.map(dto,Persona.class);
	}

}
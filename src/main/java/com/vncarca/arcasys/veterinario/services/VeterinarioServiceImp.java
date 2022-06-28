package com.vncarca.arcasys.veterinario.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.model.PersonaDto;
import com.vncarca.arcasys.veterinario.model.Veterinario;
import com.vncarca.arcasys.veterinario.model.VeterinarioDTO;
import com.vncarca.arcasys.veterinario.repository.VeterinarioRepository;

@Service
@Transactional
public class VeterinarioServiceImp implements VeterinarioService {
	@Autowired
	VeterinarioRepository veterinarioRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public Page<Veterinario> findAll(Pageable pageable) {

		return veterinarioRepository.findAll(pageable);
	}

	@Override
	public List<Veterinario> findAll() {

		return veterinarioRepository.findAll();
	}

	@Override
	public Veterinario save(Veterinario Veterinario) {

		return veterinarioRepository.save(Veterinario);
	}

	@Override
	public Veterinario findById(Long id) {

		return veterinarioRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
				"Veterinario con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public void delete(Long id) {

		veterinarioRepository.deleteById(id);
	}

	@Override
	public Page<Veterinario> findByCedula(Pageable pageable, String cedula) {

		return veterinarioRepository.findByPersona_cedulaIs(pageable, cedula);
	}

	@Override
	public Page<Veterinario> findByCedulaLike(Pageable pageable, String cedula) {
		return veterinarioRepository.findByPersona_cedulaContaining(pageable, cedula);
	}

	@Override
	public VeterinarioDTO convertToDto(Veterinario V) {
		VeterinarioDTO veterinarioDTO = new VeterinarioDTO();
		veterinarioDTO.setId(V.getId());
		veterinarioDTO.setCargo(V.getCargo());
		veterinarioDTO.setPersona(mapper.map(V.getPersona(), PersonaDto.class));
		return veterinarioDTO;
	}

	@Override
	public Veterinario convertToEntity(VeterinarioDTO V) {
		return new Veterinario(V.getId(), V.getCargo(), mapper.map(V.getPersona(), Persona.class));
	}

	@Override
	public Veterinario findByPersonaId(Long id) {
		return veterinarioRepository.findByPersonaId(id).orElseThrow(() -> new NoSuchElementException(
				"Veterinario con persona ID: " + id.toString() + " no existe en el servidor"));
	}
}

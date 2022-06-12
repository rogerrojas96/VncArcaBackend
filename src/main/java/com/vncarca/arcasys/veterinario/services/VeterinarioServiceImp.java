package com.vncarca.arcasys.veterinario.services;

import java.util.Collection;
import java.util.List;

import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.model.PersonaDto;
import com.vncarca.arcasys.veterinario.model.Veterinario;
import com.vncarca.arcasys.veterinario.model.VeterinarioDTO;
import com.vncarca.arcasys.veterinario.repository.VeterinarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioServiceImp implements VeterinarioService {
	@Autowired
	VeterinarioRepository veterinarioRepository;

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

		return veterinarioRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {

		veterinarioRepository.deleteById(id);
	}

	private VeterinarioDTO convertVeterinarioDTO(Veterinario veterinario) {
		VeterinarioDTO veterinarioDTO = new VeterinarioDTO();
		veterinarioDTO.setId(veterinario.getId());
		veterinarioDTO.setCargo(veterinario.getCargo());
		veterinarioDTO.setNombreCompleto(veterinario.getPersona().toString());
		veterinarioDTO.setCedula(veterinario.getPersona().getCedula());
		return veterinarioDTO;
	}

	private PersonaDto ConvertPersonaDTO(Persona persona) {
		PersonaDto personaDto = new PersonaDto();
		personaDto.setApellidos(persona.getApellidos());
		personaDto.setCedula(persona.getCedula());
		personaDto.setNombre(persona.getNombre());
		return personaDto;
	}

	@Override
	public Page<Veterinario> findByCedula(Pageable pageable, String cedula) {

		return veterinarioRepository.findByPersona_cedulaIs(pageable, cedula);
	}

	@Override
	public Page<Veterinario> findByCedulaLike(Pageable pageable, String cedula) {
		return veterinarioRepository.findByPersona_cedulaContaining(pageable, cedula);
	}
}

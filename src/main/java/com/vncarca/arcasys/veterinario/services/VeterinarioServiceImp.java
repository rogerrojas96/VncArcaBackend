package com.vncarca.arcasys.veterinario.services;

import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.model.PersonaDto;
import com.vncarca.arcasys.persona.services.PersonaService;
import com.vncarca.arcasys.veterinario.model.Veterinario;
import com.vncarca.arcasys.veterinario.model.VeterinarioDTO;
import com.vncarca.arcasys.veterinario.repository.VeterinarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class VeterinarioServiceImp implements VeterinarioService {
	@Autowired
	VeterinarioRepository veterinarioRepository;

	@Autowired
	ModelMapper mapper;

	@Autowired
	PersonaService personaService;

	@Override
	public Page<VeterinarioDTO> findAll(Pageable pageable) {

		return veterinarioRepository.findAll(pageable).map(this::convertToDto);
	}

	@Override
	public List<VeterinarioDTO> findAll() {

		return veterinarioRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public VeterinarioDTO save(VeterinarioDTO veterinario) {

		return convertToDto(veterinarioRepository.save(convertToEntity(veterinario)));
	}

	@Override
	public VeterinarioDTO findById(Long id) {

		return veterinarioRepository.findById(id).map(this::convertToDto).orElseThrow(() -> new NoSuchElementException(
				"Veterinario con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public void delete(Long id) {

		veterinarioRepository.deleteById(id);
	}

	@Override
	public Page<VeterinarioDTO> findByCedula(Pageable pageable, String cedula) {

		return veterinarioRepository.findByPersona_cedulaIs(pageable, cedula).map(this::convertToDto);
	}

	@Override
	public Page<VeterinarioDTO> findByCedulaLike(Pageable pageable, String cedula) {
		return veterinarioRepository.findByPersona_cedulaContaining(pageable, cedula).map(this::convertToDto);
	}

	@Override
	public VeterinarioDTO convertToDto(Veterinario v) {
//		mapper.map(v.getPersona(), PersonaDto.class);
		return  new VeterinarioDTO(v.getId(),v.getCargo(),personaService.convertToDto(v.getPersona()));
	}

	@Override
	public Veterinario convertToEntity(VeterinarioDTO v) {
		return new Veterinario(v.getId(),v.getCargo(),personaService.convertToEntity(v.getPersona()));
	}

	@Override
	public Veterinario findByPersonaId(Long id) {
		return veterinarioRepository.findByPersonaId(id).orElseThrow(() -> new NoSuchElementException(
				"Veterinario con persona ID: " + id.toString() + " no existe en el servidor"));
	}
}

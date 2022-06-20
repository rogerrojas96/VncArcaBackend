package com.vncarca.arcasys.fichaclinica.services;

import java.util.List;
import java.util.stream.Collectors;

import com.vncarca.arcasys.fichaclinica.model.FichaClinica;
import com.vncarca.arcasys.fichaclinica.repository.FichaClinicaRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vncarca.arcasys.animal.model.Animal;
import com.vncarca.arcasys.animal.model.AnimalDTO;
import com.vncarca.arcasys.fichaclinica.model.FichaClinicaDTO;
import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.model.PersonaDto;
import com.vncarca.arcasys.veterinario.model.Veterinario;
import com.vncarca.arcasys.veterinario.model.VeterinarioDTO;

@Service
public class FichaClinicaServiceImp implements FichaClinicaService {

	@Autowired
	FichaClinicaRepository fichaClinicaRepository;

	@Override
	public Page<FichaClinica> findAll(Pageable pageable) {
		return fichaClinicaRepository.findAll(pageable);
	}

	@Override
	public FichaClinica save(FichaClinica fichaClinica) {

		return fichaClinicaRepository.save(fichaClinica);
	}

	// @Override
	// public List<FichaClinicaDTO> findAll() {
	// return
	// fichaClinicaRepository.findAll().stream().map(this::fichaClinicaToDTO).collect(Collectors.toList());
	// }

	@Override
	public List<FichaClinica> findAll() {
		return fichaClinicaRepository.findAll();
	}

	@Override
	public FichaClinica findById(Long id) {
		return fichaClinicaRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		fichaClinicaRepository.deleteById(id);
	}

	@Override
	public Page<FichaClinica> findBytipoPacienteContaining(Pageable pageable, String tipoPaciente) {
		return fichaClinicaRepository.findBytipoPacienteContaining(pageable, tipoPaciente);
	}

	@Override
	public FichaClinicaDTO fichaClinicaToDTO(FichaClinica fichaClinica) {
		FichaClinicaDTO fichaClinicaDTO = new FichaClinicaDTO();
		fichaClinicaDTO.setId(fichaClinica.getId());
		fichaClinicaDTO.setAlimentacion(fichaClinica.getAlimentacion());
		// fichaClinicaDTO.setAnimal(AnimalToDTO(fichaClinica.getAnimal()));
		fichaClinicaDTO.setConjuntiva(fichaClinica.getConjuntiva());
		fichaClinicaDTO.setCosto(fichaClinica.getCosto());
		fichaClinicaDTO.setDiagnosticoDiferencial(fichaClinica.getDiagnosticoDiferencial());
		fichaClinicaDTO.setEsterilizacion(fichaClinica.getEsterilizacion());
		fichaClinicaDTO.setFechaIngreso(fichaClinica.getFechaIngreso());
		fichaClinicaDTO.setFrecuenciaCardiaca(fichaClinica.getFrecuenciaCardiaca());
		fichaClinicaDTO.setFrecuenciaRespiratoria(fichaClinica.getFrecuenciaRespiratoria());
		fichaClinicaDTO.setHallazgos(fichaClinica.getHallazgos());
		fichaClinicaDTO.setMotivoConsulta(fichaClinica.getMotivoConsulta());
		fichaClinicaDTO.setMucosas(fichaClinica.getMucosas());
		fichaClinicaDTO.setPronostico(fichaClinica.getPronostico());
		fichaClinicaDTO.setTRC(fichaClinica.getPronostico());
		fichaClinicaDTO.setTemperatura(fichaClinica.getTemperatura());
		fichaClinicaDTO.setVeterinario(VeterinarioToDTO(fichaClinica.getVeterinario()));
		return fichaClinicaDTO;
	}

	private VeterinarioDTO VeterinarioToDTO(Veterinario veterinario) {
		VeterinarioDTO veterinarioDTO = new VeterinarioDTO();
		veterinarioDTO.setId(veterinario.getId());
		veterinarioDTO.setCargo(veterinario.getCargo());
		veterinarioDTO.setNombreCompleto(PersonaToDTO(veterinario.getPersona()));
		veterinarioDTO.setCedula(veterinario.getPersona().getCedula());
		return veterinarioDTO;
	}

	private String PersonaToDTO(Persona persona) {
		PersonaDto personaDto = new PersonaDto();
		personaDto.setApellidos(persona.getApellidos());
		personaDto.setNombre(persona.getNombre());
		return personaDto.toString();
	}

	private AnimalDTO AnimalToDTO(Animal animal) {
		AnimalDTO animalDto = new AnimalDTO();
		animalDto.setId(animal.getId());
		animalDto.setNombre(animal.getNombre());
		animalDto.setFechaNacimiento(animal.getFechaNacimiento());
		animalDto.setFoto(animal.getFoto());
		animalDto.setLugarEstancia(animal.getLugarEstancia());
		animalDto.setSexo(animal.getSexo());
		return animalDto;
	}

	@Override
	public List<FichaClinicaDTO> findByanimalId(Long id) {

		return fichaClinicaRepository.findByanimalId(id).stream().map(this::fichaClinicaToDTO).collect(Collectors.toList());
	}
}

// List<FichaClinicaDTO> list = new
// ArrayList<>();fichaClinicaRepository.findAll().iterator().forEachRemaining((f)->list.add(fichaClinicaToDTO(f)));
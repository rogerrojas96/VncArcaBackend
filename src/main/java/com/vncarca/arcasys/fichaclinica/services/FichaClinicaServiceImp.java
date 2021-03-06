package com.vncarca.arcasys.fichaclinica.services;

import com.vncarca.arcasys.animal.services.IAnimalRefugioService;
import com.vncarca.arcasys.fichaclinica.model.FichaClinica;
import com.vncarca.arcasys.fichaclinica.model.FichaClinicaDTO;
import com.vncarca.arcasys.fichaclinica.model.FichaClinicaRequestDTO;
import com.vncarca.arcasys.fichaclinica.repository.FichaClinicaRepository;
import com.vncarca.arcasys.veterinario.services.VeterinarioService;
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
public class FichaClinicaServiceImp implements FichaClinicaService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	FichaClinicaRepository fichaClinicaRepository;

	@Autowired
	VeterinarioService veterinarioService;

	@Autowired
	IAnimalRefugioService animalService;

	@Override
	public Page<FichaClinicaDTO> findAll(Pageable pageable) {
		return fichaClinicaRepository.findAll(pageable).map(this::convertToDto);
	}

	@Override
	public FichaClinicaDTO save(FichaClinicaRequestDTO fichaClinicaRequestDTO) {
		return convertToDto(fichaClinicaRepository.save(convertRequestToEntity(fichaClinicaRequestDTO)));
	}

	@Override
	public FichaClinicaDTO update(FichaClinicaRequestDTO fichaClinica) {
		return convertToDto(fichaClinicaRepository.save(convertRequestToEntity(fichaClinica)));
	}

	@Override
	public FichaClinica convertRequestToEntity(FichaClinicaRequestDTO fichaClinicaRequestDTO) {
		FichaClinica fichaClinica = new FichaClinica(
				fichaClinicaRequestDTO.getFechaIngreso(),
				fichaClinicaRequestDTO.getMotivoConsulta(),
				fichaClinicaRequestDTO.getHallazgos(),
				fichaClinicaRequestDTO.getTemperatura(),
				fichaClinicaRequestDTO.getConjuntiva(),
				fichaClinicaRequestDTO.getFrecuenciaCardiaca(),
				fichaClinicaRequestDTO.getFrecuenciaRespiratoria(),
				fichaClinicaRequestDTO.getTRC(),
				fichaClinicaRequestDTO.getMucosas(),
				fichaClinicaRequestDTO.getEsterilizacion(),
				fichaClinicaRequestDTO.getAlimentacion(),
				fichaClinicaRequestDTO.getPronostico(),
				fichaClinicaRequestDTO.getTipoPaciente(),
				fichaClinicaRequestDTO.getExamenes_solicitados(),
				fichaClinicaRequestDTO.getDiagnosticoDiferencial(),
				fichaClinicaRequestDTO.getCosto(),
				veterinarioService.findByPersonaId(fichaClinicaRequestDTO.getPersonaId()),
				animalService.getAnimalEntityPorId(fichaClinicaRequestDTO.getAnimalId()));

		if (fichaClinicaRepository.existsById(fichaClinicaRequestDTO.getId())) {
			FichaClinicaDTO oldFichaClinica = findById(fichaClinicaRequestDTO.getId());
			fichaClinica.setId(oldFichaClinica.getId());
			;
		}

		return fichaClinica;
	}

	@Override
	public List<FichaClinicaDTO> findAll() {
		return fichaClinicaRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public FichaClinicaDTO findById(Long id) {
		return fichaClinicaRepository.findById(id).map(this::convertToDto).orElseThrow(() -> new NoSuchElementException(
				"Ficha clinica con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public void delete(Long id) {
		fichaClinicaRepository.deleteById(id);
	}

	@Override
	public Page<FichaClinicaDTO> findBytipoPacienteContaining(Pageable pageable, String tipoPaciente) {
		return fichaClinicaRepository.findBytipoPacienteContaining(pageable, tipoPaciente).map(this::convertToDto);
	}

	@Override
	public FichaClinicaDTO convertToDto(FichaClinica fihcaClinica) {
		FichaClinicaDTO fichaClinicaDto = modelMapper.map(fihcaClinica, FichaClinicaDTO.class);
		return fichaClinicaDto;
	}

	@Override
	public List<FichaClinicaDTO> findByanimalId(Long id) {

		return fichaClinicaRepository.findByanimalId(id).stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public FichaClinica convertToEntity(FichaClinicaDTO dto) {
		return modelMapper.map(dto, FichaClinica.class);
	}
}

// @Override
// public List<FichaClinicaDTO> findAll() {
// return
// fichaClinicaRepository.findAll().stream().map(this::fichaClinicaToDTO).collect(Collectors.toList());
// }
// List<FichaClinicaDTO> list = new
// ArrayList<>();fichaClinicaRepository.findAll().iterator().forEachRemaining((f)->list.add(fichaClinicaToDTO(f)));

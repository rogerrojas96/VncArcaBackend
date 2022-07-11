package com.vncarca.arcasys.carnetVacunacion.vacuna.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.vncarca.arcasys.carnetVacunacion.vacuna.model.Vacuna;
import com.vncarca.arcasys.carnetVacunacion.vacuna.model.VacunaDTO;
import com.vncarca.arcasys.carnetVacunacion.vacuna.repository.VacunaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VacunaServicesImp implements VacunaServices {
	@Autowired
	VacunaRepository vacunaRepository;

	@Override
	public Page<VacunaDTO> findAll(Pageable pageable) {
		return vacunaRepository.findAll(pageable).map(this::convertToDto);
	}

	@Override
	public List<VacunaDTO> findAll() {
		return vacunaRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public VacunaDTO save(VacunaDTO vacuna) {
		return convertToDto(vacunaRepository.save(convertToEntity(vacuna)));
	}

	@Override
	public VacunaDTO findById(Long id) {
		return vacunaRepository.findById(id).map(this::convertToDto).orElseThrow(() -> new NoSuchElementException(
				"Vacuna con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public void delete(Long id) {
		vacunaRepository.deleteById(id);
	}

	@Override
	public VacunaDTO convertToDto(Vacuna vacuna) {
		return new VacunaDTO(vacuna.getId(),vacuna.getNombre(),vacuna.getTipo(),vacuna.getDescripcion());
	}

	@Override
	public Vacuna convertToEntity(VacunaDTO vacunaDTO) {
		return new Vacuna(vacunaDTO.getId(),vacunaDTO.getNombre(),vacunaDTO.getTipo(),vacunaDTO.getDescripcion());
	}
}

package com.vncarca.arcasys.tratamiento.services;

import com.vncarca.arcasys.fichaclinica.model.FichaClinica;
import com.vncarca.arcasys.fichaclinica.repository.FichaClinicaRepository;
import com.vncarca.arcasys.fichaclinica.services.FichaClinicaService;
import com.vncarca.arcasys.tratamiento.model.Tratamiento;
import com.vncarca.arcasys.tratamiento.model.TratamientoDto;
import com.vncarca.arcasys.tratamiento.model.TratamientoDtoExtends;
import com.vncarca.arcasys.tratamiento.repository.TratamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TratamientoServiceImp implements TratamientoService {
	@Autowired
	TratamientoRepository tratamientoRepository;
	
	@Autowired
	FichaClinicaRepository fichaClinicaRepository;
	
	@Autowired
	FichaClinicaService fichaClinicaService;
	
	@Override
	public Page<TratamientoDtoExtends> findAll(Pageable pageable) {
		
		return tratamientoRepository.findAll(pageable).map(this::convertToDtoExtends);
	}
	
	@Override
	public TratamientoDtoExtends save(TratamientoDto tratamientoDto, Long idFicha) {
		FichaClinica fichaClinica = fichaClinicaRepository.findById(idFicha).orElse(null);
		if (fichaClinica != null) {
			Tratamiento tratamiento = new Tratamiento();
			tratamiento.setDescripcion(tratamientoDto.getDescripcion());
			tratamiento.setEstado(tratamientoDto.getEstado());
			tratamiento.setFechaAplicacion(tratamientoDto.getFechaAplicacion());
			tratamiento.setIndicaciones(tratamientoDto.getIndicaciones());
			tratamiento.setIdFichaClinica(fichaClinica);
			return convertToDtoExtends(tratamientoRepository.save(tratamiento));
		}
		return null;
	}
	
	@Override
	public List<TratamientoDtoExtends> findAll() {
		
		return tratamientoRepository.findAll().stream().map(this::convertToDtoExtends).collect(Collectors.toList());
	}
	
	@Override
	public TratamientoDtoExtends findById(Long id) {
		
		return tratamientoRepository.findById(id).map(this::convertToDtoExtends).orElseThrow(() -> new NoSuchElementException(
				"Tratamiento con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public boolean delete(Long idTratamiento) {
		if(tratamientoRepository.existsById(idTratamiento)){
			tratamientoRepository.deleteById(idTratamiento);
			return true;
		}
		return false;
	}
	
	@Override
	public List<TratamientoDtoExtends> findByFichaClinica(Long idFicha) {
		return tratamientoRepository.getByFichaClinica(idFicha).stream().map(this::convertToDtoExtends).collect(Collectors.toList());
	}
	
	@Override
	public TratamientoDtoExtends update(TratamientoDto tratamientoDto, Long idTratamiento, Long idFicha) {
		Tratamiento tratamiento = tratamientoRepository.findById(idTratamiento).orElse(null);
		FichaClinica fichaClinica = fichaClinicaRepository.findById(idFicha).orElse(null);
		if (fichaClinica != null && tratamiento != null) {
			tratamiento.setDescripcion(tratamientoDto.getDescripcion());
			tratamiento.setEstado(tratamientoDto.getEstado());
			tratamiento.setFechaAplicacion(tratamientoDto.getFechaAplicacion());
			tratamiento.setIndicaciones(tratamientoDto.getIndicaciones());
			tratamiento.setIdFichaClinica(fichaClinica);
			return convertToDtoExtends(tratamientoRepository.save(tratamiento));
		}
		return null;
	}
	
	public TratamientoDtoExtends convertToDtoExtends(Tratamiento t) {
		return new TratamientoDtoExtends(t.getFechaAplicacion(), t.getDescripcion(), t.getIndicaciones(), t.getEstado(), t.getId(), fichaClinicaService.convertToDto(t.getIdFichaClinica()));
	}
	
}

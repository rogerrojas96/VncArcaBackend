package com.vncarca.arcasys.tratamiento.services;

import java.util.List;

import com.vncarca.arcasys.fichaclinica.model.FichaClinica;
import com.vncarca.arcasys.fichaclinica.repository.FichaClinicaRepository;
import com.vncarca.arcasys.tratamiento.model.Tratamiento;
import com.vncarca.arcasys.tratamiento.model.TratamientoDto;
import com.vncarca.arcasys.tratamiento.repository.TratamientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TratamientoServiceImp implements TratamientoService{
    @Autowired
    TratamientoRepository tratamientoRepository;

	@Autowired
	FichaClinicaRepository fichaClinicaRepository;

	@Override
	public Page<Tratamiento> findAll(Pageable pageable) {
		
		return tratamientoRepository.findAll(pageable);
	}

	@Override
	public Tratamiento save(TratamientoDto tratamientoDto, Long idFicha) {
		FichaClinica fichaClinica = fichaClinicaRepository.findById(idFicha).orElse(null);
		if(fichaClinica!=null){
			Tratamiento tratamiento = new Tratamiento();
			tratamiento.setDescripcion(tratamientoDto.getDescripcion());
			tratamiento.setEstado(tratamientoDto.getEstado());
			tratamiento.setFechaAplicacion(tratamientoDto.getFechaAplicacion());
			tratamiento.setIndicaciones(tratamientoDto.getIndicaciones());
			tratamiento.setIdFichaClinica(fichaClinica);
			return tratamientoRepository.save(tratamiento);
		}
		return null;
	}

	@Override
	public List<Tratamiento> findAll() {
		
		return tratamientoRepository.findAll();
	}

	@Override
	public Tratamiento findById(Long id) {
		
		return tratamientoRepository.findById(id).orElse(null);
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
	public List<Tratamiento> findByFichaClinica(Long idFicha) {
		return tratamientoRepository.getByFichaClinica(idFicha);
	}

	@Override
	public Tratamiento update(TratamientoDto tratamientoDto, Long idTratamiento, Long idFicha) {
		Tratamiento tratamiento = tratamientoRepository.findById(idTratamiento).orElse(null);
		FichaClinica fichaClinica = fichaClinicaRepository.findById(idFicha).orElse(null);
		if(fichaClinica != null && tratamiento != null){
			tratamiento.setDescripcion(tratamientoDto.getDescripcion());
			tratamiento.setEstado(tratamientoDto.getEstado());
			tratamiento.setFechaAplicacion(tratamientoDto.getFechaAplicacion());
			tratamiento.setIndicaciones(tratamientoDto.getIndicaciones());
			tratamiento.setIdFichaClinica(fichaClinica);
			return tratamientoRepository.save(tratamiento);
		}
		return null;
	}
	
}

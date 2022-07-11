package com.vncarca.arcasys.tratamiento.services;

import com.vncarca.arcasys.tratamiento.model.Tratamiento;
import com.vncarca.arcasys.tratamiento.model.TratamientoDto;
import com.vncarca.arcasys.tratamiento.model.TratamientoDtoExtends;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TratamientoService {
	public Page<TratamientoDtoExtends> findAll(Pageable pageable);
	
	public TratamientoDtoExtends save(TratamientoDto tratamientoDto, Long idFicha);
	
	public List<TratamientoDtoExtends> findAll();
	
	public TratamientoDtoExtends findById(Long id);
	
	public boolean delete(Long id);
	
	public List<TratamientoDtoExtends> findByFichaClinica(Long idFicha);
	
	public TratamientoDtoExtends update(TratamientoDto tratamientoDto, Long idTratamiento, Long idFicha);
	
	TratamientoDtoExtends convertToDtoExtends(Tratamiento tratamiento);
}

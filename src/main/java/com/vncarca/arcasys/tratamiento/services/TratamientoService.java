package com.vncarca.arcasys.tratamiento.services;

import java.util.List;
import com.vncarca.arcasys.tratamiento.model.Tratamiento;
import com.vncarca.arcasys.tratamiento.model.TratamientoDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TratamientoService {
    public Page<Tratamiento> findAll(Pageable pageable);
	public Tratamiento save(TratamientoDto tratamientoDto, Long idFicha);
	public List<Tratamiento> findAll();
	public Tratamiento findById(Long id);
	public boolean delete(Long id);
	public List<Tratamiento> findByFichaClinica(Long idFicha); 
	public Tratamiento update(TratamientoDto tratamientoDto, Long idTratamiento, Long idFicha);
}

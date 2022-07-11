package com.vncarca.arcasys.carnetVacunacion.vacuna.services;

import java.util.List;

import com.vncarca.arcasys.carnetVacunacion.vacuna.model.VacunaDTO;
import com.vncarca.arcasys.carnetVacunacion.vacuna.model.Vacuna;

import com.vncarca.arcasys.globalService.GlobalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VacunaServices extends GlobalService<VacunaDTO,Vacuna> {
	public Page<VacunaDTO> findAll(Pageable pageable);

	public List<VacunaDTO> findAll();

	public VacunaDTO save(VacunaDTO vacuna);

	public VacunaDTO findById(Long id);

	public void delete(Long id);
}

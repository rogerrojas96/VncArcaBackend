package com.vncarca.arcasys.vacuna.services;

import java.util.List;

import com.vncarca.arcasys.vacuna.model.Vacuna;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VacunaServices {
	public Page<Vacuna> findAll(Pageable pageable);

	public List<Vacuna> findAll();

	public Vacuna save(Vacuna vacuna);

	public Vacuna findById(Long id);

	public void delete(Long id);
}

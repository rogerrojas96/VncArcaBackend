package com.vncarca.arcasys.vacuna.services;

import java.util.List;

import com.vncarca.arcasys.vacuna.model.Vacuna;
import com.vncarca.arcasys.vacuna.repository.VacunaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VacunaServicesImp implements VacunaServices {
	@Autowired
	VacunaRepository vacunaRepository;

	@Override
	public Page<Vacuna> findAll(Pageable pageable) {

		return vacunaRepository.findAll(pageable);
	}

	@Override
	public List<Vacuna> findAll() {

		return vacunaRepository.findAll();
	}

	@Override
	public Vacuna save(Vacuna vacuna) {

		return vacunaRepository.save(vacuna);
	}

	@Override
	public Vacuna findById(Long id) {

		return vacunaRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		vacunaRepository.deleteById(id);
	}

}

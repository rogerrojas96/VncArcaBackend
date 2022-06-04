package com.vncarca.arcasys.medicamento.services;

import org.springframework.stereotype.Service;

import com.vncarca.arcasys.medicamento.model.Medicamento;
import com.vncarca.arcasys.medicamento.repository.MedicamentoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoServiceImp implements MedicamentoService{
	@Autowired
	private MedicamentoRepository medicamentoRepository;

	@Override
	public Page<Medicamento>findAll(Pageable pageable){
		return medicamentoRepository.findAll(pageable);

	}
	@Override
	public Medicamento save(Medicamento medicamento) {
		return medicamentoRepository.save(medicamento);
	}
	@Override
	public Medicamento findById(Long id) {
		return medicamentoRepository.findById(id).orElse(null);

	}
	@Override
	public void delete(Long id) {
		medicamentoRepository.deleteById(id);
	}
	@Override
	public Page<Medicamento> findAllByNombreComercial(Pageable pageable, String nombre) {
		return medicamentoRepository.findAllByNombreComercial(pageable,nombre);
	}

}

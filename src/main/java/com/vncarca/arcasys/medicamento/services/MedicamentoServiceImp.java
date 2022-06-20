package com.vncarca.arcasys.medicamento.services;

import org.springframework.stereotype.Service;

import com.vncarca.arcasys.medicamento.model.Medicamento;
import com.vncarca.arcasys.medicamento.repository.MedicamentoRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
	public Page<Medicamento> findAllByNombreComercialContainingOrNombreGenericoContaining(Pageable pageable, String nombreComercial,String nombreGenerico) {
		return medicamentoRepository.findAllByNombreComercialContainingOrNombreGenericoContaining(pageable,nombreComercial,nombreGenerico);
	}
	@Override
	public List<Medicamento> findAll() {
		return medicamentoRepository.findAll();
	}
	@Override
	public List<Medicamento> findByNombreComercialContainingOrNombreGenericoContaining(String nombreComercial,
			String nombreGenerico) {
		return medicamentoRepository.findByNombreComercialContainingOrNombreGenericoContaining(nombreComercial, nombreGenerico);
	}

}

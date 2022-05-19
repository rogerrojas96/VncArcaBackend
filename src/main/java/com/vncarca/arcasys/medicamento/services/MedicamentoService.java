package com.vncarca.arcasys.medicamento.services;

import com.vncarca.arcasys.medicamento.model.Medicamento;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface MedicamentoService {
	public Page<Medicamento> findAll(Pageable pageable);
	// METODO PARA GUARDAR LOS DATOS DE UN Medicamento
	public Medicamento save(Medicamento medicamento);
	// METODO PARA OBTENER LOS DATOS DE UN Medicamento POR ID
	public Medicamento findById(Long id);
	// METODO PARA ELIMINAR UN Medicamento
	public void delete(Long id);

}

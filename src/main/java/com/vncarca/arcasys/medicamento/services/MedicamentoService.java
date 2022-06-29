package com.vncarca.arcasys.medicamento.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.medicamento.model.Medicamento;

public interface MedicamentoService {
	public Page<Medicamento> findAll(Pageable pageable);
	public List<Medicamento> findAll();
	public Page<Medicamento> findAllByNombreComercialContainingOrNombreGenericoContaining(Pageable pageable,String nombreComercial,String nombreGenerico);
	public List<Medicamento> findByNombreComercialContainingOrNombreGenericoContaining(String nombreComercial,String nombreGenerico);
	// METODO PARA GUARDAR LOS DATOS DE UN Medicamento
	public Medicamento save(Medicamento medicamento);
	// METODO PARA OBTENER LOS DATOS DE UN Medicamento POR ID
	public Medicamento findById(Long id);
	// METODO PARA ELIMINAR UN Medicamento
	public void delete(Long id);

}

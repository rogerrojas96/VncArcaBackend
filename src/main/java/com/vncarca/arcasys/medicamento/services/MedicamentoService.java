package com.vncarca.arcasys.medicamento.services;

import java.util.List;

import com.vncarca.arcasys.globalService.GlobalService;
import com.vncarca.arcasys.medicamento.model.MedicamentoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.medicamento.model.Medicamento;

public interface MedicamentoService extends GlobalService<MedicamentoDto,Medicamento> {
	public Page<MedicamentoDto> findAll(Pageable pageable);
	public List<MedicamentoDto> findAll();
	public Page<MedicamentoDto> findAllByNombreComercialContainingOrNombreGenericoContaining(Pageable pageable,String nombreComercial,String nombreGenerico);
	public List<MedicamentoDto> findByNombreComercialContainingOrNombreGenericoContaining(String nombreComercial,String nombreGenerico);
	// METODO PARA GUARDAR LOS DATOS DE UN Medicamento
	public MedicamentoDto save(MedicamentoDto medicamento);
	// METODO PARA OBTENER LOS DATOS DE UN Medicamento POR ID
	public MedicamentoDto findById(Long id);
	// METODO PARA ELIMINAR UN Medicamento
	public void delete(Long id);

}

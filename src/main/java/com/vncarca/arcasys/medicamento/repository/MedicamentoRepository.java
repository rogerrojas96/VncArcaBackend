package com.vncarca.arcasys.medicamento.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vncarca.arcasys.medicamento.model.Medicamento;
@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{
	public Page<Medicamento> findAll(Pageable pageable);
	public Page<Medicamento> findAllByNombreComercialContainingOrNombreGenericoContaining(Pageable pageable,String nombreComercial,String nombreGenerico);
	public List<Medicamento> findByNombreComercialContainingOrNombreGenericoContaining(String nombreComercial,String nombreGenerico);

}

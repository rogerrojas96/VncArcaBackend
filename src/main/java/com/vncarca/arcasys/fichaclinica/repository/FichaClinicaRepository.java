package com.vncarca.arcasys.fichaclinica.repository;

import com.vncarca.arcasys.fichaclinica.model.FichaClinica;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaClinicaRepository extends JpaRepository<FichaClinica, Long> {
	public Page<FichaClinica> findBytipoPacienteContaining(Pageable pageable, String tipoPaciente);

	List<FichaClinica> findByanimalId(Long animalId);
}

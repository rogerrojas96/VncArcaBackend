package com.vncarca.arcasys.carnetVacunacion.vacuna.repository;

import com.vncarca.arcasys.carnetVacunacion.vacuna.model.Vacuna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacunaRepository extends JpaRepository<Vacuna, Long> {

}

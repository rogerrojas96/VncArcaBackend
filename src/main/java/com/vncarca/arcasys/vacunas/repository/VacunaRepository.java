package com.vncarca.arcasys.vacunas.repository;

import com.vncarca.arcasys.vacunas.model.Vacuna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VacunaRepository extends JpaRepository<Vacuna, Long>{
    
}

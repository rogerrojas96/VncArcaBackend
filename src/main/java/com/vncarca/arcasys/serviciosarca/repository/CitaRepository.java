package com.vncarca.arcasys.serviciosarca.repository;

import com.vncarca.arcasys.serviciosarca.model.Cita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long>{
    
}

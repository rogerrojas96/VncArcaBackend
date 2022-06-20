package com.vncarca.arcasys.serviciosarca.repository;

import com.vncarca.arcasys.serviciosarca.model.ServicioArca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioArcaRepository extends JpaRepository<ServicioArca, Long>{
    
}

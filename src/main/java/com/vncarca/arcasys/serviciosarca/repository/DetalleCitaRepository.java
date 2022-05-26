package com.vncarca.arcasys.serviciosarca.repository;

import com.vncarca.arcasys.serviciosarca.model.DetalleCita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCitaRepository extends JpaRepository<DetalleCita, Long>{
    
}

package com.vncarca.arcasys.adopciones.repository;

import com.vncarca.arcasys.adopciones.model.Adopcion;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AdopcionRepository extends JpaRepository<Adopcion, Long>{
    
 
}

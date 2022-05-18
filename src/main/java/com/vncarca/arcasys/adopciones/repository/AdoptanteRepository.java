package com.vncarca.arcasys.adopciones.repository;

import java.util.Optional;

import com.vncarca.arcasys.adopciones.model.Adoptante;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptanteRepository extends JpaRepository<Adoptante, Long>{
    
    boolean existsByCedula(String cedula);
    Optional<Adoptante> findById(Long id);
}

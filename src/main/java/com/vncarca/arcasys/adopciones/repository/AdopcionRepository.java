package com.vncarca.arcasys.adopciones.repository;

import com.vncarca.arcasys.adopciones.model.Adopcion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdopcionRepository extends JpaRepository<Adopcion, Long>{
    
    @Query(value = "select usuarios.id from usuarios where usuarios.id = :id;", nativeQuery = true)
    public Long getId(Long id);
}

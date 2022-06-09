package com.vncarca.arcasys.donaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vncarca.arcasys.donaciones.model.Donacion;

@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Long>{
    
    @Query(value = "Select * from donaciones where donaciones.id_persona = :idPersona", nativeQuery = true)
    public List<Donacion> getAllDonacionesPorPersona(Long idPersona);
}

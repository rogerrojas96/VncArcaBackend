package com.vncarca.arcasys.tratamiento.repository;


import com.vncarca.arcasys.tratamiento.model.Tratamiento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento,Long>{
    public Page<Tratamiento> findAll(Pageable pageable);

    @Query(value = "Select * from tratamientos where tratamientos.id_ficha_clinica = :idFicha",nativeQuery = true)
    public List<Tratamiento> getByFichaClinica(Long idFicha);  
}

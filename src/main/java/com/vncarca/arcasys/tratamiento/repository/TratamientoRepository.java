package com.vncarca.arcasys.tratamiento.repository;


import com.vncarca.arcasys.tratamiento.model.Tratamiento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento,Long>{
    public Page<Tratamiento> findAll(Pageable pageable);

    
}

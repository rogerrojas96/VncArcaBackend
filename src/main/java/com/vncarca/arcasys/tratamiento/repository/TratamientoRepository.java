package com.vncarca.arcasys.tratamiento.repository;


import com.vncarca.arcasys.tratamiento.model.Tratamiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
    public Page<Tratamiento> findAll(Pageable pageable);

    public List<Tratamiento> findAllByIdFichaClinicaId(Long idFichaClinica);
}
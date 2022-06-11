package com.vncarca.arcasys.voluntarios.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vncarca.arcasys.voluntarios.model.Voluntario;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario,Long>{
    public Page<Voluntario> findAll(Pageable pageable);
}

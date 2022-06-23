package com.vncarca.arcasys.voluntarios.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.voluntarios.model.Voluntario;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario,Long>{
    public Page<Voluntario> findAll(Pageable pageable);
    public Optional <Voluntario> findByPersona(Persona persona);
}

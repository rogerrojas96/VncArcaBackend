package com.vncarca.arcasys.adopciones.repository;



import com.vncarca.arcasys.adopciones.model.Adoptante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdoptanteRepository extends JpaRepository<Adoptante, Long>{

    @Query(value = "select adoptantes.id from adoptantes where adoptantes.id_persona = :idPersona", nativeQuery = true)
    public Long getId(Long idPersona);
}

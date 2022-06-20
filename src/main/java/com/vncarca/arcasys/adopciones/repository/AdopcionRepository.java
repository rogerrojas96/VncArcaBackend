package com.vncarca.arcasys.adopciones.repository;

import java.util.List;

import com.vncarca.arcasys.adopciones.model.Adopcion;
import com.vncarca.arcasys.animal.model.Animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdopcionRepository extends JpaRepository<Adopcion, Long>{
    
    @Query(value = "Select * from adopciones where adopciones.id_adoptante = :idAdoptante", nativeQuery = true)
    public List<Adopcion> getAdopcionesPorIdAdoptante(Long idAdoptante);

    public boolean existsByAnimal(Animal animal);
}

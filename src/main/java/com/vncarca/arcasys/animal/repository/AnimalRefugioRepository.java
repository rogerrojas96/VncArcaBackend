package com.vncarca.arcasys.animal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vncarca.arcasys.animal.model.AnimalRefugio;

@Repository
public interface AnimalRefugioRepository extends JpaRepository<AnimalRefugio, Long>, JpaSpecificationExecutor<AnimalRefugio> {

    @Query(
        value = "Select * from animales_refugio where animales_refugio.adoptado = :adoptado", 
        countQuery = "select count(*) from animales_refugio", 
        nativeQuery = true)
    Page<AnimalRefugio> getAnimalesPorestadoAdopcion(boolean adoptado, Pageable pageable);
}

package com.vncarca.arcasys.serviciosarca.repository;

import java.util.Date;
import java.util.List;

import com.vncarca.arcasys.serviciosarca.model.Cita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long>{
    
    @Query(value = "select * from citas where citas.fecha_cita = :fechaAgenda", nativeQuery = true)
    public List<Cita> getCitasPorFecha(Date fechaAgenda);

    @Query(value = "select * from citas where citas.id_veterinario = :idVeterinario", nativeQuery = true)
    public List<Cita> getCitasPorVeterinario(Long idVeterinario);
}

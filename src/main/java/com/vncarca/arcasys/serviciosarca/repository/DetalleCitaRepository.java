package com.vncarca.arcasys.serviciosarca.repository;

import java.util.List;

import com.vncarca.arcasys.serviciosarca.model.DetalleCita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCitaRepository extends JpaRepository<DetalleCita, Long>{
    
    @Query(value = "Select * from detalles_citas where detalles_citas.id_cita = :idCita", nativeQuery = true)
    public List<DetalleCita> getDetallesCita(Long idCita);

    @Query(value = "Select * from detalles_citas where detalles_citas.id_servicio = :idServicio", nativeQuery = true)
    public List<DetalleCita> getDetallesCitaByIdServicio(Long idServicio);
}

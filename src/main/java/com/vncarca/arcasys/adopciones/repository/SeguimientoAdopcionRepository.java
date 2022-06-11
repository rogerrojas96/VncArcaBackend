package com.vncarca.arcasys.adopciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vncarca.arcasys.adopciones.model.SeguimientoAdopcion;

@Repository
public interface SeguimientoAdopcionRepository extends JpaRepository<SeguimientoAdopcion, Long>{
    
    @Query(
        value = "Select distinct seguimientos_adopciones.id_adopcion from seguimientos_adopciones where seguimientos_adopciones.estado_seguimiento = :estado", 
        nativeQuery = true)
    public List<Long> getAllIdsAdopcionesEnSeguimientoActivo(boolean estado);

    @Query(
        value = "select * from seguimientos_adopciones where seguimientos_adopciones.id_adopcion = :idAdopcion and seguimientos_adopciones.estado_seguimiento = :estado", 
        nativeQuery = true)
    public List<SeguimientoAdopcion> getAllSeguimientosByEstado(Long idAdopcion, boolean estado);

    @Query(
        value = "select count(seguimientos_adopciones.id_adopcion) from seguimientos_adopciones where seguimientos_adopciones.id_adopcion = :idAdopcion and seguimientos_adopciones.estado_seguimiento = :estado", 
        nativeQuery = true)
    public Long getNumSeguientosPorEstado(Long idAdopcion, boolean estado); 
}
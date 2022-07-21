package com.vncarca.arcasys.serviciosarca.repository;

import java.util.Date;
import java.util.List;

import com.vncarca.arcasys.serviciosarca.model.Cita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long>{
    
    @Query(
        value = "select * from citas where extract(day from citas.fecha_cita) = :dia and extract(month from citas.fecha_cita) = :mes and extract(year from citas.fecha_cita) = :anio", 
        nativeQuery = true)
    public List<Cita> getCitasPorFecha(int dia, int mes, int anio);

    @Query(value = "select * from citas where citas.id_veterinario = :idVeterinario", nativeQuery = true)
    public List<Cita> getCitasPorVeterinario(Long idVeterinario);

    public boolean existsByFechaCita(Date fechaCita); 

    @Query(
        value = "select citas.* from citas join cliente on citas.cliente_id = cliente.id join personas on cliente.id = personas.id where personas.cedula = :cedula and citas.estado = :estado", 
        nativeQuery = true
    )
    public List<Cita> getCitasActivasPorCliente(String cedula, boolean estado);
}
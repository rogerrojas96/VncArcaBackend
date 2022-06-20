package com.vncarca.arcasys.medicacion.repository;

//import java.util.Date;
import java.util.List;

import com.vncarca.arcasys.medicacion.model.Medicacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicacionRepository extends JpaRepository<Medicacion, Long>{

    @Query(
        value = "select * from medicacion where extract(day from medicacion.fecha_caducidad) = :dia and extract(month from medicacion.fecha_caducidad) = :mes and extract(year from medicacion.fecha_caducidad) = :anio", 
        nativeQuery = true
    )
    public List<Medicacion> getMedicacionPorFecha(int dia, int mes, int anio);
    
    @Query(value = "select * from medicacion where medicacion.id_medicamento = :idMedicamento", nativeQuery = true)
    public List<Medicacion> getCitasPorMedicamento(Long idMedicamento);

}

package com.vncarca.arcasys.medicacion.services;

import java.util.List;

import com.vncarca.arcasys.medicacion.dto.MedicacionDto;
import com.vncarca.arcasys.medicacion.model.Medicacion;

public interface IMedicacionService {
    public List<Medicacion> getAllMedicacions();
    public Medicacion getMedicacionPorId(Long idMedicacion);
    public Medicacion crearMedicacion(MedicacionDto medicacionDto, Long idMedicamento, Long idTratamiento);
    public boolean eliminarMedicacion(Long idMedicacion); 
    
}

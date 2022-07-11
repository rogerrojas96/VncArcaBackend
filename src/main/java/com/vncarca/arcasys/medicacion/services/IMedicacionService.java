package com.vncarca.arcasys.medicacion.services;

import com.vncarca.arcasys.medicacion.dto.MedicacionDto;
import com.vncarca.arcasys.medicacion.model.MedicacionDtoExtends;

import java.util.List;

public interface IMedicacionService {
    public List<MedicacionDtoExtends> getAllMedicacions();
    
    public MedicacionDtoExtends getMedicacionPorId(Long idMedicacion);
    
    public MedicacionDtoExtends crearMedicacion(MedicacionDto medicacionDto, Long idMedicamento, Long idTratamiento);
    
    public boolean eliminarMedicacion(Long idMedicacion);
    
    public List<MedicacionDtoExtends> getMedicacionPorTratamiento(Long idTratamiento);
}

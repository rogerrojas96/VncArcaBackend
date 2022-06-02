package com.vncarca.arcasys.medicacion.services;

import java.util.List;

import com.vncarca.arcasys.medicacion.model.Medicacion;

public interface MedicacionServiceImp {
    public List<Medicacion> getAllMedicacions();
    public Medicacion getMedicacionPorId(Long idMedicacion);
    public Medicacion crearMedicacion(Medicacion medicacion, Long idMedicamento);
    public Medicacion modificarMedicacion(Medicacion medicacion, Long idMedicacion, Long idMedicamento);
    public boolean eliminarMedicacion(Long idMedicacion); 
    
}

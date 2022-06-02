package com.vncarca.arcasys.medicacion.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.vncarca.arcasys.medicacion.model.Medicacion;
import com.vncarca.arcasys.medicamento.model.Medicamento;
import com.vncarca.arcasys.medicacion.repository.MedicacionRepository;
import com.vncarca.arcasys.medicamento.repository.MedicamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicacionService implements MedicacionServiceImp{ 

    @Autowired
    private MedicacionRepository medicacionRepository;

    @Override
    public List<Medicacion> getAllMedicacions() {
        return medicacionRepository.findAll();
    }

    @Override
    public Medicacion getMedicacionPorId(Long idMedicacion) {
     
        return medicacionRepository.getById(idMedicacion);
    }

    @Override
    public Medicacion crearMedicacion(Medicacion medicacion, Long idMedicamento) {
        
        return medicacionRepository.save(medicacion);
    }

    @Override
    public Medicacion modificarMedicacion(Medicacion medicacion, Long idMedicacion, Long idMedicamento) {
        
        return null;
    }

    @Override
    public boolean eliminarMedicacion(Long idMedicacion) {
        medicacionRepository.deleteById(idMedicacion);
        return false;
    }

    
}

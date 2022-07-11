package com.vncarca.arcasys.medicacion.services;

import java.util.List;

import com.vncarca.arcasys.medicacion.dto.MedicacionDto;
import com.vncarca.arcasys.medicacion.model.Medicacion;
import com.vncarca.arcasys.medicacion.repository.MedicacionRepository;
import com.vncarca.arcasys.medicamento.model.Medicamento;
import com.vncarca.arcasys.medicamento.repository.MedicamentoRepository;
import com.vncarca.arcasys.tratamiento.model.Tratamiento;
import com.vncarca.arcasys.tratamiento.repository.TratamientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicacionServiceImpl implements IMedicacionService{ 

    @Autowired
    private MedicacionRepository medicacionRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;


    @Override
    public List<Medicacion> getAllMedicacions() {
        return medicacionRepository.findAll();
    }

    @Override
    public Medicacion getMedicacionPorId(Long idMedicacion) {
         
        return medicacionRepository.findById(idMedicacion).orElse(null);
    }

    @Override
    public Medicacion crearMedicacion(MedicacionDto medicacionDto, Long idMedicamento, Long idTratamiento ) {
        Medicamento medicamento = medicamentoRepository.findById(idMedicamento).orElse(null);
        Tratamiento tratamiento = tratamientoRepository.findById(idTratamiento).orElse(null);
        if(medicamento != null && tratamiento != null){
            Medicacion medicacion = new Medicacion();
            medicacion.setDescripcionMd(medicacionDto.getDescripcionMd());
            medicacion.setDosis(medicacionDto.getDosis());
            medicacion.setDuracion(medicacionDto.getDuracion());
            medicacion.setFechaCaducidad(medicacionDto.getFechaCaducidad());
            medicacion.setFrecuencia(medicacionDto.getFrecuencia());
            medicacion.setMedicamento(medicamento);
            medicacion.setTratamiento(tratamiento);
            return medicacionRepository.save(medicacion);
        }

        return null;
    }

    @Override
    public boolean eliminarMedicacion(Long idMedicacion) {
        if(medicacionRepository.existsById(idMedicacion)){
            medicacionRepository.deleteById(idMedicacion);
            return true;
        }
        return false;
    }

    @Override
    public List<Medicacion> getMedicacionPorTratamiento(Long idTratamiento) {
        
        return medicacionRepository.getMedicamentosPorIdTratamiento(idTratamiento);
    }
    
}

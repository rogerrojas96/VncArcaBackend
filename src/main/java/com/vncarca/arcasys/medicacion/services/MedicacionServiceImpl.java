package com.vncarca.arcasys.medicacion.services;

import com.vncarca.arcasys.medicacion.dto.MedicacionDto;
import com.vncarca.arcasys.medicacion.model.Medicacion;
import com.vncarca.arcasys.medicacion.model.MedicacionDtoExtends;
import com.vncarca.arcasys.medicacion.repository.MedicacionRepository;
import com.vncarca.arcasys.medicamento.model.Medicamento;
import com.vncarca.arcasys.medicamento.repository.MedicamentoRepository;
import com.vncarca.arcasys.medicamento.services.MedicamentoService;
import com.vncarca.arcasys.tratamiento.model.Tratamiento;
import com.vncarca.arcasys.tratamiento.repository.TratamientoRepository;
import com.vncarca.arcasys.tratamiento.services.TratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MedicacionServiceImpl implements IMedicacionService {
    
    @Autowired
    private MedicacionRepository medicacionRepository;
    
    @Autowired
    private MedicamentoRepository medicamentoRepository;
    
    @Autowired
    private TratamientoRepository tratamientoRepository;
    
    @Autowired
    private MedicamentoService medicamentoService;
    @Autowired
    private TratamientoService tratamientoService;
    
    @Override
    public List<MedicacionDtoExtends> getAllMedicacions() {
        return medicacionRepository.findAll().stream().map(this::convertToDtoExtends).collect(Collectors.toList());
    }
    
    @Override
    public MedicacionDtoExtends getMedicacionPorId(Long idMedicacion) {
        return medicacionRepository.findById(idMedicacion).map(this::convertToDtoExtends).orElseThrow(() -> new NoSuchElementException(
                "Medicacion con ID: " + idMedicacion.toString() + " no existe en el servidor"));
    }
    
    @Override
    public MedicacionDtoExtends crearMedicacion(MedicacionDto medicacionDto, Long idMedicamento, Long idTratamiento) {
        Medicamento medicamento = medicamentoRepository.findById(idMedicamento).orElse(null);
        Tratamiento tratamiento = tratamientoRepository.findById(idTratamiento).orElse(null);
        if (medicamento != null && tratamiento != null) {
            Medicacion medicacion = new Medicacion();
            medicacion.setDescripcionMd(medicacionDto.getDescripcionMd());
            medicacion.setDosis(medicacionDto.getDosis());
            medicacion.setDuracion(medicacionDto.getDuracion());
            medicacion.setFechaCaducidad(medicacionDto.getFechaCaducidad());
            medicacion.setFrecuencia(medicacionDto.getFrecuencia());
            medicacion.setMedicamento(medicamento);
            medicacion.setTratamiento(tratamiento);
            return convertToDtoExtends(medicacionRepository.save(medicacion));
        }

        return null;
    }
    
    @Override
    public boolean eliminarMedicacion(Long idMedicacion) {
        if (medicacionRepository.existsById(idMedicacion)) {
            medicacionRepository.deleteById(idMedicacion);
            return true;
        }
        return false;
    }
    
    @Override
    public List<MedicacionDtoExtends> getMedicacionPorTratamiento(Long idTratamiento) {
        return medicacionRepository.getMedicamentosPorIdTratamiento(idTratamiento).stream().map(this::convertToDtoExtends).collect(Collectors.toList());
    }
    
    public MedicacionDtoExtends convertToDtoExtends(Medicacion m) {
        return new MedicacionDtoExtends(m.getDescripcionMd(), m.getDosis(), m.getFrecuencia(), m.getDuracion(), m.getFechaCaducidad(), m.getId(), medicamentoService.convertToDto(m.getMedicamento()),
                tratamientoService.convertToDtoExtends(m.getTratamiento()));
    }
    
}

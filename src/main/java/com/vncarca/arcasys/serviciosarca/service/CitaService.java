package com.vncarca.arcasys.serviciosarca.service;

import java.util.Date;
import java.util.List;

import com.vncarca.arcasys.serviciosarca.dto.CitaDto;
import com.vncarca.arcasys.serviciosarca.dto.ServicioArcaDto;
import com.vncarca.arcasys.serviciosarca.model.Cita;
import com.vncarca.arcasys.serviciosarca.model.DetalleCita;
import com.vncarca.arcasys.serviciosarca.repository.CitaRepository;
import com.vncarca.arcasys.serviciosarca.repository.DetalleCitaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaService implements ICitaService{

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private DetalleCitaRepository detalleCitaRepository;

    @Override
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    @Override
    public List<DetalleCita> getAllDetallesCita(Long idCita) {
        return detalleCitaRepository.getDetallesCita(idCita);
    }

    @Override
    public List<Cita> getCitasPorFechaAgenda(Date fechaAgenda) {
        return citaRepository.getCitasPorFecha(fechaAgenda);
    }

    @Override
    public List<Cita> getCitasPorVeterinario(Long idVeterinario) {
        return citaRepository.getCitasPorVeterinario(idVeterinario);
    }

    @Override
    public Cita getCitaPorId(Long idCita) {
        return citaRepository.findById(idCita).orElse(null);
    }

    @Override
    public Cita crearCita(CitaDto citaDto, List<ServicioArcaDto> servicios) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cita modificarCita(CitaDto citaDto, List<ServicioArcaDto> servicios, Long idCita) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean eliminarCita(Long idCita) {
        // TODO Auto-generated method stub
        return false;
    }
    
}

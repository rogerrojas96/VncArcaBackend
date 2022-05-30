package com.vncarca.arcasys.serviciosarca.service;

import java.util.List;

import com.vncarca.arcasys.serviciosarca.dto.CitaDto;
import com.vncarca.arcasys.serviciosarca.model.Cita;
import com.vncarca.arcasys.serviciosarca.model.DetalleCita;

public interface ICitaService {
    
    public List<Cita> getAllCitas();
    public List<DetalleCita>getAllDetallesCita(Long idCita);
    public List<Cita> getCitasPorFechaAgenda(String fechaAgenda);
    public List<Cita> getCitasPorVeterinario(Long idVeterinario);
    public Cita getCitaPorId(Long idCita);
    public Cita crearCita(CitaDto citaDto, Long idVeterinario);
    public Cita modificarCita(CitaDto citaDto, Long idCita, Long idVeterinario);
    public boolean eliminarCita(Long idCita); 
}

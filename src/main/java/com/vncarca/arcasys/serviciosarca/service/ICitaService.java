package com.vncarca.arcasys.serviciosarca.service;

import java.util.Date;
import java.util.List;

import com.vncarca.arcasys.serviciosarca.dto.CitaDto;
import com.vncarca.arcasys.serviciosarca.model.Cita;
import com.vncarca.arcasys.serviciosarca.model.DetalleCita;
import com.vncarca.arcasys.serviciosarca.model.ServicioArca;

public interface ICitaService {
    
    public List<Cita> getAllCitas();
    public List<DetalleCita>getAllDetallesCita(Long idCita);
    public List<Cita> getCitasPorFechaAgenda(Date fechaAgenda);
    public List<Cita> getCitasPorVeterinario(Long idVeterinario);
    public Cita getCitaPorId(Long idCita);
    public Cita crearCita(CitaDto citaDto, List<ServicioArca> servicios, Long idVeterinario);
    public Cita modificarCita(CitaDto citaDto, List<ServicioArca> servicios, Long idCita, Long idVeterinario);
    public boolean eliminarCita(Long idCita);
}

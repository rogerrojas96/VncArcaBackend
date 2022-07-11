package com.vncarca.arcasys.serviciosarca.service;

import java.util.List;

import com.vncarca.arcasys.globalService.GlobalService;
import com.vncarca.arcasys.serviciosarca.dto.CitaServiciosArca;
import com.vncarca.arcasys.serviciosarca.dto.CitaDto;
import com.vncarca.arcasys.serviciosarca.dto.CitaDtoExtends;
import com.vncarca.arcasys.serviciosarca.model.Cita;
import com.vncarca.arcasys.serviciosarca.dto.DetalleCitaDto;

public interface ICitaService  extends GlobalService<CitaDto, Cita> {
    
    public List<CitaDtoExtends> getAllCitas();
    public List<DetalleCitaDto>getAllDetallesCita(Long idCita);
    public List<CitaDtoExtends> getCitasPorFechaAgenda(String fechaAgenda);
    public List<CitaDtoExtends> getCitasPorVeterinario(Long idVeterinario);
    public CitaDtoExtends getCitaPorId(Long idCita);
    public CitaDtoExtends crearCita(CitaServiciosArca citaDto, Long idVeterinario);
    public CitaDtoExtends modificarCita(CitaServiciosArca citaDto, Long idCita, Long idVeterinario);
    public boolean eliminarCita(Long idCita); 
}

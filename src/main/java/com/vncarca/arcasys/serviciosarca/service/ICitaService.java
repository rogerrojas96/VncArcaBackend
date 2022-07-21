package com.vncarca.arcasys.serviciosarca.service;

import com.vncarca.arcasys.globalService.GlobalService;
import com.vncarca.arcasys.serviciosarca.dto.CitaArcaExtends;
import com.vncarca.arcasys.serviciosarca.dto.CitaDto;
import com.vncarca.arcasys.serviciosarca.dto.CitaServiciosArca;
import com.vncarca.arcasys.serviciosarca.dto.DetalleCitaDto;
import com.vncarca.arcasys.serviciosarca.model.Cita;

import java.util.List;

public interface ICitaService extends GlobalService<CitaDto, Cita> {

    public List<CitaArcaExtends> getAllCitas();

    public List<CitaArcaExtends> getAllCitasActivasPorCliente(String cedula);

    public List<DetalleCitaDto> getAllDetallesCita(Long idCita);

    public List<CitaArcaExtends> getCitasPorFechaAgenda(String fechaAgenda);

    public List<CitaArcaExtends> getCitasPorVeterinario(Long idVeterinario);

    public CitaArcaExtends getCitaPorId(Long idCita);

    public CitaArcaExtends crearCita(CitaServiciosArca citaDto, Long idVeterinario);

    public CitaArcaExtends modificarCita(CitaServiciosArca citaDto, Long idCita, Long idVeterinario);

    public boolean eliminarCita(Long idCita);

    public List<String> getHorasDisponibles(String fechaAgenda);
}
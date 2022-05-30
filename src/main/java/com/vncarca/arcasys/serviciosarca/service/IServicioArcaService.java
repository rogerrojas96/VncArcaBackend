package com.vncarca.arcasys.serviciosarca.service;

import java.util.List;

import com.vncarca.arcasys.serviciosarca.dto.ServicioArcaDto;
import com.vncarca.arcasys.serviciosarca.model.ServicioArca;

public interface IServicioArcaService {
    
    public List<ServicioArca> getAllServiciosArca();
    public ServicioArca getOneServicioArca(Long id);
    public ServicioArca crearServicioArca(ServicioArcaDto servicioArcaDto);
    public ServicioArca editarServicioArca(ServicioArcaDto servicioArcaDto, Long id);
    public boolean eliminarServicioArca(Long id);
}

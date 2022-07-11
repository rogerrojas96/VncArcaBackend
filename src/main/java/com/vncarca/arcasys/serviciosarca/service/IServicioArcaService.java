package com.vncarca.arcasys.serviciosarca.service;

import java.util.List;

import com.vncarca.arcasys.globalService.GlobalService;
import com.vncarca.arcasys.serviciosarca.dto.ServicioArcaDto;
import com.vncarca.arcasys.serviciosarca.model.ServicioArca;
import com.vncarca.arcasys.serviciosarca.dto.ServicioArcaDtoExtends;

public interface IServicioArcaService extends GlobalService<ServicioArcaDtoExtends,ServicioArca> {
    
    public List<ServicioArcaDtoExtends> getAllServiciosArca();
    public ServicioArcaDtoExtends getOneServicioArca(Long id);
    public ServicioArcaDtoExtends crearServicioArca(ServicioArcaDto servicioArcaDto);
    public ServicioArcaDtoExtends editarServicioArca(ServicioArcaDto servicioArcaDto, Long id);
    public boolean eliminarServicioArca(Long id);
}

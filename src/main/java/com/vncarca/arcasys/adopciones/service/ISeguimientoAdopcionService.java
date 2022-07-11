package com.vncarca.arcasys.adopciones.service;

import com.vncarca.arcasys.adopciones.dto.AdopcionDtoExtends;
import com.vncarca.arcasys.adopciones.dto.SeguimientoAdopcionDto;
import com.vncarca.arcasys.adopciones.model.SeguimientoAdopcionDtoExtends;
import com.vncarca.util.Response;

import java.util.List;

public interface ISeguimientoAdopcionService {
    public Response<SeguimientoAdopcionDtoExtends> crearSeguimiento(SeguimientoAdopcionDto seguimientoDto, Long idAdopcion);
    
    public List<SeguimientoAdopcionDtoExtends> getAllSeguimientosTerminados(Long idAdopcion);
    
    public List<SeguimientoAdopcionDtoExtends> getAllSeguimientosActivos(Long idAdopcion);
    
    public SeguimientoAdopcionDtoExtends editarSeguimiento(SeguimientoAdopcionDto seguimientoDto, Long idSeguimiento);
    
    public SeguimientoAdopcionDtoExtends finalizarSeguimiento(SeguimientoAdopcionDto seguimientoDto, Long idSeguimiento);
    
    public SeguimientoAdopcionDtoExtends eliminarSeguimiento(Long idSeguimiento);
    
    public Long getNumSeguimientosActivos(Long idAdopcion);
    
    public List<AdopcionDtoExtends> getAdopcionesEnSeguimientoActivo();
}

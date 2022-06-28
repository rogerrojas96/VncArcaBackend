package com.vncarca.arcasys.adopciones.service;

import java.util.List;

import com.vncarca.arcasys.adopciones.dto.SeguimientoAdopcionDto;
import com.vncarca.arcasys.adopciones.model.Adopcion;
import com.vncarca.arcasys.adopciones.model.SeguimientoAdopcion;
import com.vncarca.util.Response;

public interface ISeguimientoAdopcionService {
    public Response<SeguimientoAdopcion> crearSeguimiento(SeguimientoAdopcionDto seguimientoDto, Long idAdopcion);
    public List<SeguimientoAdopcion> getAllSeguimientosTerminados(Long idAdopcion);
    public List<SeguimientoAdopcion> getAllSeguimientosActivos(Long idAdopcion);
    public SeguimientoAdopcion editarSeguimiento(SeguimientoAdopcionDto seguimientoDto, Long idSeguimiento);
    public SeguimientoAdopcion finalizarSeguimiento(SeguimientoAdopcionDto seguimientoDto, Long idSeguimiento);
    public SeguimientoAdopcion eliminarSeguimiento(Long idSeguimiento);
    public Long getNumSeguimientosActivos(Long idAdopcion);
    public List<Adopcion> getAdopcionesEnSeguimientoActivo();
}

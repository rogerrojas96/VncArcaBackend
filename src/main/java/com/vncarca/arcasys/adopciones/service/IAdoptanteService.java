package com.vncarca.arcasys.adopciones.service;

import java.util.List;

import com.vncarca.arcasys.adopciones.dto.AdoptanteDto;
import com.vncarca.arcasys.adopciones.model.Adoptante;

public interface IAdoptanteService {
    
    public List<Adoptante> getAllAdoptantes();
    public Adoptante getAdoptantePorCedula(String cedula);
    public Adoptante getAdoptantePorId(Long idAdoptante);
    public Adoptante crearAdoptante(AdoptanteDto adoptanteDto);
    public Adoptante actualizarAdoptante(AdoptanteDto adoptanteDto, Long idAdoptante);
    public boolean eliminarAdoptante(Long idAdoptante);
    public Long getIdAdoptante(String cedula);
}

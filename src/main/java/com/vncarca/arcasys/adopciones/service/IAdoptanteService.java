package com.vncarca.arcasys.adopciones.service;

import com.vncarca.arcasys.adopciones.dto.AdoptanteDto;
import com.vncarca.arcasys.adopciones.model.Adoptante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAdoptanteService {
    
    public Page<Adoptante> getAllAdoptantes(Pageable pageable);
    public Adoptante getAdoptantePorCedula(String cedula);
    public Adoptante getAdoptantePorId(Long idAdoptante);
    public Adoptante crearAdoptante(AdoptanteDto adoptanteDto);
    public Adoptante actualizarAdoptante(AdoptanteDto adoptanteDto, Long idAdoptante);
    public boolean eliminarAdoptante(Long idAdoptante);
    public Long getIdAdoptante(String cedula);
}

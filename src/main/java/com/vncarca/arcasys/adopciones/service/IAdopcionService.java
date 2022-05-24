package com.vncarca.arcasys.adopciones.service;

import java.util.List;

import com.vncarca.arcasys.adopciones.dto.AdopcionDto;
import com.vncarca.arcasys.adopciones.model.Adopcion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAdopcionService { 
    
    public Page<Adopcion> getAllAdopciones(Pageable pageable);
    public List<Adopcion> getAdopcionesPorCedulaAdoptante(String cedula);
    public List<Adopcion> getAdopcionesPorIdAdoptante(Long idAdoptante);
    public Adopcion getAdopcionPorId(Long idAdopcion);
    public Adopcion crearAdocion(AdopcionDto adopcionDto, Long idAdoptante, Long idAnimal);
    public Adopcion actualizarAdocion(AdopcionDto adopcionDto, Long idAdoptante, Long idAnimal);
    public boolean eliminarAdopcion(Long idAdopcion);
}

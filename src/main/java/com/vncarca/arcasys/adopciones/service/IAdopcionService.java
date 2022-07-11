package com.vncarca.arcasys.adopciones.service;

import com.vncarca.arcasys.adopciones.dto.AdopcionDto;
import com.vncarca.arcasys.adopciones.dto.AdopcionDtoExtends;
import com.vncarca.arcasys.adopciones.model.Adopcion;

import java.util.List;

public interface IAdopcionService {
    public List<AdopcionDtoExtends> getAllAdopciones();
    public List<AdopcionDtoExtends> getAdopcionesPorCedulaAdoptante(String cedula);
    public List<AdopcionDtoExtends> getAdopcionesPorIdAdoptante(Long idAdoptante);
    public AdopcionDtoExtends getAdopcionPorId(Long idAdopcion);
    public AdopcionDtoExtends crearAdocion(AdopcionDto adopcionDto, Long idAdoptante, Long idAnimal);
    public AdopcionDtoExtends actualizarAdocion(AdopcionDto adopcionDto, Long idAdoptante, Long idAnimal);
    public boolean eliminarAdopcion(Long idAdopcion);
    AdopcionDtoExtends convertToDtoExtends(Adopcion a);
}

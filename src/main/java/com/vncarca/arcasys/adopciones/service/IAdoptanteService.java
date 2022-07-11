package com.vncarca.arcasys.adopciones.service;

import com.vncarca.arcasys.adopciones.dto.AdoptanteDto;
import com.vncarca.arcasys.adopciones.dto.AdoptanteDtoExtends;
import com.vncarca.arcasys.adopciones.model.Adoptante;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IAdoptanteService {
    
    public List<AdoptanteDtoExtends> getAllAdoptantes();
    public AdoptanteDtoExtends getAdoptantePorCedula(String cedula);
    public AdoptanteDtoExtends getAdoptantePorId(Long idAdoptante);
    public AdoptanteDtoExtends crearAdoptante(AdoptanteDto adoptanteDto);
    public AdoptanteDtoExtends actualizarAdoptante(AdoptanteDto adoptanteDto, Long idAdoptante);
    public boolean eliminarAdoptante(Long idAdoptante);
    public Long getIdAdoptante(String cedula);
    AdoptanteDtoExtends convertToDto(Adoptante adoptante);
}

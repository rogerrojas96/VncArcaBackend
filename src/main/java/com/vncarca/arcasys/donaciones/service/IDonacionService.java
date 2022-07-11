package com.vncarca.arcasys.donaciones.service;

import com.vncarca.arcasys.donaciones.Dto.DonacionDto;
import com.vncarca.arcasys.donaciones.model.DonacionDtoExtends;

import java.util.List;

public interface IDonacionService {
    
    public List<DonacionDtoExtends> getAllDonaciones();
    
    public List<DonacionDtoExtends> getAllDonacionesPorPersona(Long idPersona);
    
    public DonacionDtoExtends getDonacionPorId(Long idDonacion);
    
    public DonacionDtoExtends crearDonacion(DonacionDto donacionDto, Long idPersona);
    
    public DonacionDtoExtends editarDonacion(DonacionDto donacionDto, Long idPersona, Long idDonacion);
    
    public DonacionDtoExtends eliminarDonacion(Long idDonacion);
}

package com.vncarca.arcasys.donaciones.service;

import java.util.List;

import com.vncarca.arcasys.donaciones.Dto.DonacionDto;
import com.vncarca.arcasys.donaciones.model.Donacion;

public interface IDonacionService {
    
    public List<Donacion> getAllDonaciones();
    public List<Donacion> getAllDonacionesPorPersona(Long idPersona);
    public Donacion getDonacionPorId(Long idDonacion);
    public Donacion crearDonacion(DonacionDto donacionDto, Long idPersona);
    public Donacion editarDonacion(DonacionDto donacionDto, Long idPersona, Long idDonacion);
    public Donacion eliminarDonacion(Long idDonacion);
}

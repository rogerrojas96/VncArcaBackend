package com.vncarca.arcasys.donaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vncarca.arcasys.donaciones.Dto.DonacionDto;
import com.vncarca.arcasys.donaciones.model.Donacion;
import com.vncarca.arcasys.donaciones.repository.DonacionRepository;
import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.repository.PersonaRepository;

@Service
public class DonacionServiceImpl implements IDonacionService{

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private DonacionRepository donacionRepository;


    @Override
    public Donacion crearDonacion(DonacionDto donacionDto, Long idPersona) {
        Persona persona = personaRepository.findById(idPersona).orElse(null);
        if(persona != null){
            Donacion donacion = new Donacion();
            donacion.setCantidad(donacionDto.getCantidad());
            donacion.setDescripcion(donacionDto.getDescripcion());
            donacion.setPersona(persona);
            return donacionRepository.save(donacion);
        }

        return null;
    }

    @Override
    public Donacion eliminarDonacion(Long idDonacion) {
        Donacion donacion = donacionRepository.findById(idDonacion).orElse(null);
        if(donacion != null)
            donacionRepository.deleteById(idDonacion);
        return donacion;
    }

    @Override
    public List<Donacion> getAllDonaciones() {
        return donacionRepository.findAll();
    }

    @Override
    public List<Donacion> getAllDonacionesPorPersona(Long idPersona) {
        if(personaRepository.existsById(idPersona))
            return donacionRepository.getAllDonacionesPorPersona(idPersona);
        return null;
    }

    @Override
    public Donacion getDonacionPorId(Long idDonacion) {
        return donacionRepository.findById(idDonacion).orElse(null);
    }

    @Override
    public Donacion editarDonacion(DonacionDto donacionDto, Long idPersona, Long idDonacion) {
        if(donacionRepository.existsById(idDonacion) && personaRepository.existsById(idPersona)){
            Donacion donacion = donacionRepository.findById(idDonacion).get();
            donacion.setCantidad(donacionDto.getCantidad());
            donacion.setDescripcion(donacionDto.getDescripcion());
            donacion.setPersona(personaRepository.findById(idPersona).get());
            return donacionRepository.save(donacion);
        }
        return null;
    }
    
}

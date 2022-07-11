package com.vncarca.arcasys.donaciones.service;

import com.vncarca.arcasys.donaciones.Dto.DonacionDto;
import com.vncarca.arcasys.donaciones.model.Donacion;
import com.vncarca.arcasys.donaciones.model.DonacionDtoExtends;
import com.vncarca.arcasys.donaciones.repository.DonacionRepository;
import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import com.vncarca.arcasys.persona.repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DonacionServiceImpl implements IDonacionService {
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private DonacionRepository donacionRepository;
    
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public DonacionDtoExtends crearDonacion(DonacionDto donacionDto, Long idPersona) {
        Persona persona = personaRepository.findById(idPersona).orElse(null);
        if (persona != null) {
            Donacion donacion = new Donacion();
            donacion.setCantidad(donacionDto.getCantidad());
            donacion.setDescripcion(donacionDto.getDescripcion());
            donacion.setPersona(persona);
            return convertToDtoExtends(donacionRepository.save(donacion));
        }
        
        return null;
    }
    
    @Override
    public DonacionDtoExtends eliminarDonacion(Long idDonacion) {
        Donacion donacion = donacionRepository.findById(idDonacion).orElse(null);
        if (donacion != null)
            donacionRepository.deleteById(idDonacion);
        return convertToDtoExtends(Objects.requireNonNull(donacion));
    }
    
    @Override
    public List<DonacionDtoExtends> getAllDonaciones() {
        return donacionRepository.findAll().stream().map(this::convertToDtoExtends).collect(Collectors.toList());
    }
    
    @Override
    public List<DonacionDtoExtends> getAllDonacionesPorPersona(Long idPersona) {
        if (personaRepository.existsById(idPersona))
            return donacionRepository.getAllDonacionesPorPersona(idPersona).stream().map(this::convertToDtoExtends).collect(Collectors.toList());
        return null;
    }
    
    @Override
    public DonacionDtoExtends getDonacionPorId(Long idDonacion) {
        return donacionRepository.findById(idDonacion).map(this::convertToDtoExtends).orElseThrow(() -> new NoSuchElementException(
                "Donacion con ID: " + idDonacion.toString() + " no existe en el servidor"));
    }
    
    @Override
    public DonacionDtoExtends editarDonacion(DonacionDto donacionDto, Long idPersona, Long idDonacion) {
        if (donacionRepository.existsById(idDonacion) && personaRepository.existsById(idPersona)) {
            Donacion donacion = donacionRepository.findById(idDonacion).get();
            donacion.setCantidad(donacionDto.getCantidad());
            donacion.setDescripcion(donacionDto.getDescripcion());
            donacion.setPersona(personaRepository.findById(idPersona).get());
            return convertToDtoExtends(donacionRepository.save(donacion));
        }
        return null;
    }
    
    public DonacionDtoExtends convertToDtoExtends(Donacion d) {
        return new DonacionDtoExtends(d.getDescripcion(), d.getCantidad(), d.getId(), modelMapper.map(d.getPersona(),
                PersonaDtoExtends.class));
    }
    
    
}

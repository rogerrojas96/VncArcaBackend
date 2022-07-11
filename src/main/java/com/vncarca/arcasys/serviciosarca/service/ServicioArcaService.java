package com.vncarca.arcasys.serviciosarca.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.vncarca.arcasys.serviciosarca.dto.ServicioArcaDto;
import com.vncarca.arcasys.serviciosarca.model.DetalleCita;
import com.vncarca.arcasys.serviciosarca.model.ServicioArca;
import com.vncarca.arcasys.serviciosarca.dto.ServicioArcaDtoExtends;
import com.vncarca.arcasys.serviciosarca.repository.DetalleCitaRepository;
import com.vncarca.arcasys.serviciosarca.repository.ServicioArcaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioArcaService implements IServicioArcaService{

    @Autowired
    private ServicioArcaRepository servicioArcaRepository;

    @Autowired
    private DetalleCitaRepository detalleCitaRepository;


    @Override
    public List<ServicioArcaDtoExtends> getAllServiciosArca() {
        return servicioArcaRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }


    @Override
    public ServicioArcaDtoExtends getOneServicioArca(Long id) {
        return servicioArcaRepository.findById(id).map(this::convertToDto).orElseThrow(()-> new NoSuchElementException(
                "Servicio con ID: " + id.toString() + " no existe en el servidor"));
    }


    @Override
    public ServicioArcaDtoExtends crearServicioArca(ServicioArcaDto servicioArcaDto) {
        return convertToDto(servicioArcaRepository.save(toEntity(servicioArcaDto)));
    }


    @Override
    public ServicioArcaDtoExtends editarServicioArca(ServicioArcaDto servicioArcaDto, Long id) {
        ServicioArca servicioArca =convertToEntity(getOneServicioArca(id));
        if(servicioArca != null){
            servicioArca.setDescripcion(servicioArcaDto.getDescripcion());
            servicioArca.setNombre(servicioArcaDto.getNombre().toUpperCase());
            servicioArca.setPrecio(servicioArcaDto.getPrecio());
            return convertToDto(servicioArcaRepository.save(servicioArca));
        }
        return null;
    }


    @Override
    public boolean eliminarServicioArca(Long id) {
        if(servicioArcaRepository.existsById(id)){
            List<DetalleCita> detallesCita = detalleCitaRepository.getDetallesCitaByIdServicio(id);
            for (DetalleCita detalleCita : detallesCita) {
                detalleCitaRepository.deleteById(detalleCita.getId());
            }
            servicioArcaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ServicioArcaDtoExtends convertToDto(ServicioArca s) {
        return new ServicioArcaDtoExtends(s.getNombre(), s.getDescripcion(), s.getPrecio(),s.getId());
    }

    @Override
    public ServicioArca convertToEntity(ServicioArcaDtoExtends s) {
        return  new ServicioArca(s.getId(),s.getNombre().toUpperCase(),s.getDescripcion(),s.getPrecio());
    }

    public ServicioArca toEntity(ServicioArcaDto s) {
        return  new ServicioArca(s.getNombre().toUpperCase(),s.getDescripcion(),s.getPrecio());
    }
}

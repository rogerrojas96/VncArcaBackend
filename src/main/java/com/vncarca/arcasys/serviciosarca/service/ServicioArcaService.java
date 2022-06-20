package com.vncarca.arcasys.serviciosarca.service;

import java.util.List;

import com.vncarca.arcasys.serviciosarca.dto.ServicioArcaDto;
import com.vncarca.arcasys.serviciosarca.model.DetalleCita;
import com.vncarca.arcasys.serviciosarca.model.ServicioArca;
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
    public List<ServicioArca> getAllServiciosArca() {
        return servicioArcaRepository.findAll();
    }


    @Override
    public ServicioArca getOneServicioArca(Long id) {
        return servicioArcaRepository.findById(id).orElse(null);
    }


    @Override
    public ServicioArca crearServicioArca(ServicioArcaDto servicioArcaDto) {
        ServicioArca servicioArca = new ServicioArca();
        servicioArca.setPrecio(servicioArcaDto.getPrecio());
        servicioArca.setDescripcion(servicioArcaDto.getDescripcion());
        servicioArca.setNombre(servicioArcaDto.getNombre().toUpperCase());
        return servicioArcaRepository.save(servicioArca);
    }


    @Override
    public ServicioArca editarServicioArca(ServicioArcaDto servicioArcaDto, Long id) {
        ServicioArca servicioArca = servicioArcaRepository.findById(id).orElse(null);
        if(servicioArca != null){
            servicioArca.setDescripcion(servicioArcaDto.getDescripcion());
            servicioArca.setNombre(servicioArcaDto.getNombre().toUpperCase());
            servicioArca.setPrecio(servicioArcaDto.getPrecio());
            return servicioArcaRepository.save(servicioArca); 
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
}

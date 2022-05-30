package com.vncarca.arcasys.serviciosarca.service;

import java.util.Date;
import java.util.List;

import com.vncarca.arcasys.serviciosarca.dto.CitaDto;
import com.vncarca.arcasys.serviciosarca.model.Cita;
import com.vncarca.arcasys.serviciosarca.model.DetalleCita;
import com.vncarca.arcasys.serviciosarca.model.ServicioArca;
import com.vncarca.arcasys.serviciosarca.repository.CitaRepository;
import com.vncarca.arcasys.serviciosarca.repository.DetalleCitaRepository;
import com.vncarca.arcasys.serviciosarca.repository.ServicioArcaRepository;
import com.vncarca.arcasys.veterinario.model.Veterinario;
import com.vncarca.arcasys.veterinario.repository.VeterinarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaService implements ICitaService{

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private DetalleCitaRepository detalleCitaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository; 

    @Autowired
    private ServicioArcaRepository servicioArcaRepository;



    @Override
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }


    @Override
    public List<DetalleCita> getAllDetallesCita(Long idCita) {
        return detalleCitaRepository.getDetallesCita(idCita);
    }


    @Override
    public List<Cita> getCitasPorFechaAgenda(Date fechaAgenda) {
        return citaRepository.getCitasPorFecha(fechaAgenda);
    }


    @Override
    public List<Cita> getCitasPorVeterinario(Long idVeterinario) {
        return citaRepository.getCitasPorVeterinario(idVeterinario);
    }


    @Override
    public Cita getCitaPorId(Long idCita) {
        return citaRepository.findById(idCita).orElse(null);
    }


    @Override
    public Cita crearCita(CitaDto citaDto, List<ServicioArca> servicios, Long idVeterinario) {
        if(!citaRepository.existsByFechaCita(citaDto.getFechaCita()) && veterinarioRepository.existsById(idVeterinario)){
            Cita cita = new Cita();
            cita.setEstado(citaDto.isEstado());
            cita.setFechaCita(citaDto.getFechaCita());
            cita.setMotivo(citaDto.getMotivo());
            cita.setNombreCliente(citaDto.getNombreCliente());
            cita.setVeterinario(veterinarioRepository.findById(idVeterinario).get());

            cita = citaRepository.save(cita);

            for (ServicioArca servicioArca : servicios) {
                if(servicioArcaRepository.existsById(servicioArca.getId())){
                    DetalleCita detalleCita = new DetalleCita();
                    detalleCita.setCita(cita);
                    detalleCita.setServicioArca(servicioArcaRepository.findById(servicioArca.getId()).get());
                    detalleCitaRepository.save(detalleCita);
                }
            }
            return cita;
        }
        return null;
    }


    @Override
    public Cita modificarCita(CitaDto citaDto, List<ServicioArca> servicios, Long idCita, Long idVeterinario) {
        Veterinario veterinario = veterinarioRepository.findById(idVeterinario).orElse(null);
        Cita cita = citaRepository.findById(idCita).orElse(null);
        if(cita != null && veterinario != null){
            List<DetalleCita> detalleCitas = detalleCitaRepository.getDetallesCita(cita.getId());
            cita.setEstado(citaDto.isEstado());
            cita.setFechaCita(citaDto.getFechaCita());
            cita.setMotivo(citaDto.getMotivo());
            cita.setNombreCliente(citaDto.getNombreCliente());
            cita.setVeterinario(veterinarioRepository.findById(idVeterinario).get());
            cita = citaRepository.save(cita);

            for (DetalleCita detalleCita : detalleCitas) {
                detalleCitaRepository.deleteById(detalleCita.getId());
            }

            for (ServicioArca servicioArca : servicios) {
                if(servicioArcaRepository.existsById(servicioArca.getId())){
                    DetalleCita detalleCita = new DetalleCita();
                    detalleCita.setCita(cita);
                    detalleCita.setServicioArca(servicioArcaRepository.findById(servicioArca.getId()).get());
                    detalleCitaRepository.save(detalleCita);
                }
            }
            return cita;
        }
        return null;
    }


    @Override
    public boolean eliminarCita(Long idCita) {
        if(citaRepository.existsById(idCita)){
            citaRepository.deleteById(idCita);
            return true;
        }
        return false;
    }
}

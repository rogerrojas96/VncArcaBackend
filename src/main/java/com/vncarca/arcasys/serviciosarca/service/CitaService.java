package com.vncarca.arcasys.serviciosarca.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    public List<Cita> getCitasPorFechaAgenda(String fechaAgenda) {
        LocalDate fecha = LocalDate.parse(fechaAgenda);
        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue();
        int anio = fecha.getYear();
        return citaRepository.getCitasPorFecha(dia, mes, anio);
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
    public Cita crearCita(CitaDto citaDto, Long idVeterinario) {
        if(!citaRepository.existsByFechaCita(getDate(citaDto.getFechaCita())) && veterinarioRepository.existsById(idVeterinario)){
            Cita cita = new Cita();
            cita.setEstado(citaDto.isEstado());
            cita.setFechaCita(getDate(citaDto.getFechaCita()));
            cita.setMotivo(citaDto.getMotivo());
            cita.setNombreCliente(citaDto.getNombreCliente());
            cita.setVeterinario(veterinarioRepository.findById(idVeterinario).get());

            cita = citaRepository.save(cita);

            for (ServicioArca servicioArca : citaDto.getServicios()) {
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
    public Cita modificarCita(CitaDto citaDto, Long idCita, Long idVeterinario) {
        Veterinario veterinario = veterinarioRepository.findById(idVeterinario).orElse(null);
        Cita cita = citaRepository.findById(idCita).orElse(null);
        if(cita != null && veterinario != null){
            List<DetalleCita> detalleCitas = detalleCitaRepository.getDetallesCita(cita.getId());
            cita.setEstado(citaDto.isEstado());
            cita.setFechaCita(getDate(citaDto.getFechaCita()));
            cita.setMotivo(citaDto.getMotivo());
            cita.setNombreCliente(citaDto.getNombreCliente());
            cita.setVeterinario(veterinarioRepository.findById(idVeterinario).get());
            cita = citaRepository.save(cita);


            for (ServicioArca servicioArca : citaDto.getServicios()) {
                if(servicioArcaRepository.existsById(servicioArca.getId())){
                    DetalleCita detalleCita = new DetalleCita();
                    detalleCita.setCita(cita);
                    detalleCita.setServicioArca(servicioArcaRepository.findById(servicioArca.getId()).get());
                    detalleCitaRepository.save(detalleCita);
                }
            }
            for (DetalleCita detalleCita : detalleCitas) {
                detalleCitaRepository.deleteById(detalleCita.getId());
            }
            return cita;
        }
        return null;
    }


    @Override
    public boolean eliminarCita(Long idCita) {
        if(citaRepository.existsById(idCita)){
            List<DetalleCita> detalleCitas = detalleCitaRepository.getDetallesCita(idCita);
            citaRepository.deleteById(idCita);
            for (DetalleCita detalleCita : detalleCitas) {
                detalleCitaRepository.deleteById(detalleCita.getId());
            }
            return true;
        }
        return false;
    }



    private Date getDate(String fecha){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            return formato.parse(fecha);
        }catch(ParseException e){
            return null;
        }
    }
}

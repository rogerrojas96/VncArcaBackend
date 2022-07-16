package com.vncarca.arcasys.serviciosarca.service;

import com.vncarca.arcasys.persona.cliente.Cliente;
import com.vncarca.arcasys.persona.cliente.ClienteDtoExtends;
import com.vncarca.arcasys.persona.cliente.Services.ClienteService;
import com.vncarca.arcasys.serviciosarca.dto.*;
import com.vncarca.arcasys.serviciosarca.model.Cita;
import com.vncarca.arcasys.serviciosarca.model.DetalleCita;
import com.vncarca.arcasys.serviciosarca.repository.CitaRepository;
import com.vncarca.arcasys.serviciosarca.repository.DetalleCitaRepository;
import com.vncarca.arcasys.serviciosarca.repository.ServicioArcaRepository;
import com.vncarca.arcasys.veterinario.model.Veterinario;
import com.vncarca.arcasys.veterinario.repository.VeterinarioRepository;
import com.vncarca.arcasys.veterinario.services.VeterinarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CitaService implements ICitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private DetalleCitaRepository detalleCitaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private ServicioArcaRepository servicioArcaRepository;
    @Autowired
    private ServicioArcaService servicioArcaService;
    @Autowired
    private VeterinarioService veterinarioService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    ModelMapper mapper;

    @Override
    public List<CitaArcaExtends> getAllCitas() {
        return citaRepository.findAll().stream().map(this::convertToDtoExtends).collect(Collectors.toList());
    }


    @Override
    public List<DetalleCitaDto> getAllDetallesCita(Long idCita) {
        return detalleCitaRepository.getDetallesCita(idCita).stream().map(this::convertDetalleCitaToDto).collect(Collectors.toList());
    }


    @Override
    public List<CitaArcaExtends> getCitasPorFechaAgenda(String fechaAgenda) {
        LocalDate fecha = LocalDate.parse(fechaAgenda);
        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue();
        int anio = fecha.getYear();
        return citaRepository.getCitasPorFecha(dia, mes, anio).stream().map(this::convertToDtoExtends).collect(Collectors.toList());
    }


    @Override
    public List<CitaArcaExtends> getCitasPorVeterinario(Long idVeterinario) {
        return citaRepository.getCitasPorVeterinario(idVeterinario).stream().map(this::convertToDtoExtends).collect(Collectors.toList());
    }


    @Override
    public CitaArcaExtends getCitaPorId(Long idCita) {
        return citaRepository.findById(idCita).map(this::convertToDtoExtends).orElseThrow(() -> new NoSuchElementException(
                "Cita con ID: " + idCita.toString() + " no existe en el servidor"));
    }

    public Cita findById(Long idCita) {
        return citaRepository.findById(idCita).orElseThrow(()-> new NoSuchElementException(
                "Cita con ID: " + idCita.toString() + " no existe en el servidor"));
    }

    @Override
    public CitaArcaExtends crearCita(CitaServiciosArca citaDto, Long idVeterinario) {
        if (!citaRepository.existsByFechaCita(citaDto.getFechaCita()) && veterinarioRepository.existsById(idVeterinario)) {
            Cita cita = new Cita();
            cita.setEstado(citaDto.isEstado());
            cita.setFechaCita(citaDto.getFechaCita());
            cita.setMotivo(citaDto.getMotivo());
            cita.setCliente(clienteDtoExtendsToCliente(clienteService.findById(citaDto.getCliente_id())));
            cita.setVeterinario(veterinarioRepository.findById(idVeterinario).get());

            cita = citaRepository.save(cita);

            for (ServicioArcaDtoExtends servicioArca : citaDto.getServicios()) {
                if(servicioArcaRepository.existsById(servicioArca.getId())){
                    DetalleCita detalleCita = new DetalleCita();
                    detalleCita.setCita(cita);
                    detalleCita.setServicioArca(servicioArcaRepository.findById(servicioArca.getId()).get());
                    detalleCitaRepository.save(detalleCita);
                }
            }
            return convertToDtoExtends(cita);
        }
        return null;
    }


    @Override
    public CitaArcaExtends modificarCita(CitaServiciosArca citaDto, Long idCita, Long idVeterinario) {
        Veterinario veterinario = veterinarioRepository.findById(idVeterinario).orElse(null);
        Cita cita = findById(idCita);
        if (cita != null && veterinario != null) {
            List<DetalleCita> detalleCitas = detalleCitaRepository.getDetallesCita(cita.getId());
            cita.setEstado(citaDto.isEstado());
            cita.setFechaCita(citaDto.getFechaCita());
            cita.setMotivo(citaDto.getMotivo());
            cita.setCliente(this.clienteDtoExtendsToCliente(clienteService.findById(citaDto.getCliente_id())));
            cita.setVeterinario(veterinarioRepository.findById(idVeterinario).get());
            cita = citaRepository.save(cita);


            for (ServicioArcaDtoExtends servicioArca : citaDto.getServicios()) {
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
            return convertToDtoExtends(cita);
        }
        return null;
    }

    @Override
    public boolean eliminarCita(Long idCita) {
        if(citaRepository.existsById(idCita)){
            List<DetalleCita> detalleCitas = detalleCitaRepository.getDetallesCita(idCita);
            for (DetalleCita detalleCita : detalleCitas) {
                detalleCitaRepository.deleteById(detalleCita.getId());
            }
            citaRepository.deleteById(idCita);
            return true;
        }
        return false;
    }

    private Date getDate(String fecha){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formato.parse(fecha);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public CitaDto convertToDto(Cita cita) {
        return new CitaDto(0L, cita.getFechaCita(), cita.getMotivo(), cita.isEstado());
    }

    public CitaArcaExtends convertToDtoExtends(Cita c) {
        return new CitaArcaExtends(c.getId(), this.clienteToClienteDtoExtends(c.getCliente()), c.getFechaCita(), c.getMotivo(), c.isEstado(), veterinarioService.convertToDto(c.getVeterinario()));
    }

    @Override
    public Cita convertToEntity(CitaDto citaDto) {
        return null;
    }

    public DetalleCitaDto convertDetalleCitaToDto(DetalleCita cita) {
        return new DetalleCitaDto(cita.getId(), convertToDtoExtends(cita.getCita()), servicioArcaService.convertToDto(cita.getServicioArca()));
    }

    public ClienteDtoExtends clienteToClienteDtoExtends(Cliente c) {
        return new ClienteDtoExtends(c.getId(), c.getCedula(), c.getNombre(), c.getApellidos(), c.getDireccion(), c.getTelefono(), c.getCelular(), c.getCorreo());
    }

    public Cliente clienteDtoExtendsToCliente(ClienteDtoExtends c) {
        return new Cliente(c.getId(), c.getCedula(), c.getNombre(), c.getApellidos(), c.getDireccion(), c.getTelefono(), c.getCelular(), c.getCorreo());
    }
}
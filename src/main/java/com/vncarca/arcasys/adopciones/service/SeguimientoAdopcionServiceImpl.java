package com.vncarca.arcasys.adopciones.service;

import com.vncarca.arcasys.adopciones.dto.AdopcionDtoExtends;
import com.vncarca.arcasys.adopciones.dto.SeguimientoAdopcionDto;
import com.vncarca.arcasys.adopciones.model.Adopcion;
import com.vncarca.arcasys.adopciones.model.SeguimientoAdopcion;
import com.vncarca.arcasys.adopciones.model.SeguimientoAdopcionDtoExtends;
import com.vncarca.arcasys.adopciones.repository.AdopcionRepository;
import com.vncarca.arcasys.adopciones.repository.SeguimientoAdopcionRepository;
import com.vncarca.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeguimientoAdopcionServiceImpl implements ISeguimientoAdopcionService{

    @Value("${spring.mail.username}")
    private String correoArca;
    
    private final String ASUNTO_CORREO = "Seguimiento de animales adoptados en la Fundación ARCA.";
    
    @Autowired
    private SeguimientoAdopcionRepository seguimientoRepository;
    
    @Autowired
    private AdopcionRepository adopcionRepository;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private IAdopcionService adopcionService;
    
    @Override
    public Response<SeguimientoAdopcionDtoExtends> crearSeguimiento(SeguimientoAdopcionDto seguimientoDto, Long idAdopcion) {
        Adopcion adopcion = adopcionRepository.findById(idAdopcion).orElse(null);
        if (adopcion != null) {
            String correoAdoptante = adopcion.getAdoptante().getPersona().getCorreo();
            String mensaje = seguimientoDto.getMensajeSeguimiento();
            boolean enviado = enviarEmail(correoArca, correoAdoptante, ASUNTO_CORREO, mensaje);
            if (enviado) {
                SeguimientoAdopcion seguimiento = new SeguimientoAdopcion();
                seguimiento.setAdopcion(adopcion);
                seguimiento.setEstadoSeguimiento(true);
                seguimiento.setFechaSeguimiento(seguimientoDto.getFechaSeguimiento());
                seguimiento.setMensajeSeguimiento(mensaje);
                seguimiento.setRespuestaAdoptante(null);
                seguimiento = seguimientoRepository.save(seguimiento);
                return new Response<>(convertToDtoExtends(seguimiento), HttpStatus.CREATED);
            }else{
                return new Response<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new Response<>(null, HttpStatus.BAD_REQUEST);
    }
    
    @Override
    public SeguimientoAdopcionDtoExtends editarSeguimiento(SeguimientoAdopcionDto seguimientoDto, Long idSeguimiento) {
        SeguimientoAdopcion seguimiento = seguimientoRepository.findById(idSeguimiento).orElse(null);
        if (seguimiento != null) {
            seguimiento.setEstadoSeguimiento(seguimientoDto.isEstadoSeguimiento());
            seguimiento.setFechaSeguimiento(seguimientoDto.getFechaSeguimiento());
            seguimiento.setRespuestaAdoptante(seguimientoDto.getRespuestaAdoptante());
            return convertToDtoExtends(seguimientoRepository.save(seguimiento));
        }
        return null;
    }
    
    @Override
    public SeguimientoAdopcionDtoExtends eliminarSeguimiento(Long idSeguimiento) {
        SeguimientoAdopcion seguimiento = seguimientoRepository.findById(idSeguimiento).orElse(null);
        if (seguimiento != null)
            seguimientoRepository.deleteById(idSeguimiento);
        return convertToDtoExtends(seguimiento);
    }
    
    @Override
    public SeguimientoAdopcionDtoExtends finalizarSeguimiento(SeguimientoAdopcionDto seguimientoDto, Long idSeguimiento) {
        SeguimientoAdopcion seguimiento = seguimientoRepository.findById(idSeguimiento).orElse(null);
        String respuestaAdoptante = seguimientoDto.getRespuestaAdoptante();
        if (seguimiento != null && respuestaAdoptante != null && !respuestaAdoptante.equals("")) {
            seguimiento.setRespuestaAdoptante(respuestaAdoptante);
            seguimiento.setEstadoSeguimiento(false);
            return convertToDtoExtends(seguimientoRepository.save(seguimiento));
        }
        return null;
    }
    
    @Override
    public List<AdopcionDtoExtends> getAdopcionesEnSeguimientoActivo() {
        List<Adopcion> adopciones = new ArrayList<>();
        List<Long> idsAdopcion = seguimientoRepository.getAllIdsAdopcionesEnSeguimientoActivo(true);
        for (Long id : idsAdopcion) {
            adopciones.add(adopcionRepository.findById(id).get());
        }
        return adopciones.stream().map(adopcionService::convertToDtoExtends).collect(Collectors.toList());
    }
    
    @Override
    public List<SeguimientoAdopcionDtoExtends> getAllSeguimientosActivos(Long idAdopcion) {
        return seguimientoRepository.getAllSeguimientosByEstado(idAdopcion, true).stream().map(this::convertToDtoExtends).collect(Collectors.toList());
    }
    
    @Override
    public List<SeguimientoAdopcionDtoExtends> getAllSeguimientosTerminados(Long idAdopcion) {
        return seguimientoRepository.getAllSeguimientosByEstado(idAdopcion, false).stream().map(this::convertToDtoExtends).collect(Collectors.toList());
    }

    @Override
    public Long getNumSeguimientosActivos(Long idAdopcion) {
        return seguimientoRepository.getNumSeguientosPorEstado(idAdopcion, true);
    }


    /* --------------------------------------- MÉTODOS AUXILIARES --------------------------------------- */
    private boolean enviarEmail(String correoEmisor, String correoReceptor, String asunto, String cuerpoMensaje){
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(correoEmisor);
            mail.setTo(correoReceptor);
            mail.setSubject(asunto);
            mail.setText(cuerpoMensaje);
            javaMailSender.send(mail);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public SeguimientoAdopcionDtoExtends convertToDtoExtends(SeguimientoAdopcion s) {
        return new SeguimientoAdopcionDtoExtends(s.getMensajeSeguimiento(), s.getFechaSeguimiento(),
                s.getRespuestaAdoptante(), s.isEstadoSeguimiento(), s.getId(), adopcionService.convertToDtoExtends(s.getAdopcion()));
    }
}

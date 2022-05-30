package com.vncarca.arcasys.serviciosarca.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.vncarca.arcasys.serviciosarca.dto.CitaDto;
import com.vncarca.arcasys.serviciosarca.model.Cita;
import com.vncarca.arcasys.serviciosarca.model.DetalleCita;
import com.vncarca.arcasys.serviciosarca.model.ServicioArca;
import com.vncarca.arcasys.serviciosarca.service.ICitaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "Citas", description = "Controlador para CRUD de agendamiento de citas")
@RestController
@CrossOrigin("*")
@RequestMapping("/citas")
public class CitaController {
    
    @Autowired
    private ICitaService citaService;

    private Map<String, Object> response = new HashMap<>();
    private Cita cita;
    private HttpStatus status;


    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<Cita>> getAllCitas(){
        return new ResponseEntity<>(citaService.getAllCitas(), HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping("/detallesCita/{idCita}")
    public ResponseEntity<List<DetalleCita>> getAllDetallesCita(@PathVariable Long idCita){
        return new ResponseEntity<>(citaService.getAllDetallesCita(idCita), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/fecha/{fechaAgenda}")
    public ResponseEntity<List<Cita>> getCitasPorFechaAgenda(@PathVariable Date fechaAgenda){
        return new ResponseEntity<>(citaService.getCitasPorFechaAgenda(fechaAgenda), HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping("/veterinario/{idVeterinario}")
    public ResponseEntity<List<Cita>> getCitasPorVeterinario(@PathVariable Long idVeterinario){
        return new ResponseEntity<>(citaService.getCitasPorVeterinario(idVeterinario), HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping("/{idCita}")
    public ResponseEntity<Cita> getCitaPorId(@PathVariable Long idCita){
        return new ResponseEntity<>(citaService.getCitaPorId(idCita), HttpStatus.OK);
    }


    @ResponseBody
    @PostMapping("/{idVeterinario}")
    public ResponseEntity<?> crearCita(@Valid @RequestBody CitaDto citaDto, @Valid @RequestBody List<ServicioArca> servicios, 
            @PathVariable Long idVeterinario, BindingResult result){
        response.clear();
        if(!result.hasErrors()){
            try{
                cita = citaService.crearCita(citaDto, servicios, idVeterinario);
                if(cita != null){
                    response.put("mensaje", "cita creada con exito!");
                    response.put("cita", cita);
                    status = HttpStatus.CREATED;
                }else{
                    response.put("mensaje", "No existe el veterinario: "+idVeterinario.toString()+" o la fecha ya esta agendada!");
                    status = HttpStatus.BAD_REQUEST;
                }
            }catch(DataAccessException e){
                response.put("mensaje", "Ha ocurrido un error en el servidor al intentar crear la cita!");
                response.put("error", e.getMostSpecificCause().getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }else{
            response.put( "errors", getErrors(result));
             status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Map<String, Object>>(response, status);
    }


    @ResponseBody
    @PutMapping("/{idCita}")
    public ResponseEntity<?> modificarCita(@Valid @RequestBody CitaDto citaDto, @Valid @RequestBody List<ServicioArca> servicios,  
            @PathVariable Long idCita,  @PathVariable Long idVeterinario, BindingResult result){
        response.clear();
        if(!result.hasErrors()){
            try{
                cita = citaService.modificarCita(citaDto, servicios, idCita, idVeterinario);
                if(cita != null){
                    response.put("mensaje", "cita actualizada con exito!");
                    response.put("cita", cita);
                    status = HttpStatus.CREATED;
                }else{
                    response.put("mensaje", "No existe la cita con id: "+idCita.toString()+" o no existe el veterinario con id: "+idVeterinario.toString());
                    status = HttpStatus.BAD_REQUEST;
                }
            }catch(DataAccessException e){
                response.put("mensaje", "Ha ocurrido un error en el servidor al intentar actualizar el servicio! ");
                response.put("error", e.getMostSpecificCause().getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }else{
            response.put( "errors", getErrors(result));
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Map<String, Object>>(response, status);
    }


     /* ---------------------- MÉTODOS AUXILIARES ---------------------- */

     private List<String> getErrors(BindingResult result){
        return result.getFieldErrors().stream().map(
            err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }
        ).collect(Collectors.toList());
    }
}

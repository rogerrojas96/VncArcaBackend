package com.vncarca.arcasys.serviciosarca.controller;

import com.vncarca.arcasys.serviciosarca.dto.CitaServiciosArca;
import com.vncarca.arcasys.serviciosarca.dto.CitaDto;
import com.vncarca.arcasys.serviciosarca.dto.CitaDtoExtends;
import com.vncarca.arcasys.serviciosarca.dto.DetalleCitaDto;
import com.vncarca.arcasys.serviciosarca.service.ICitaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "Citas", description = "Controlador para CRUD de agendamiento de citas")
@RestController
@RequestMapping("/citas")
public class CitaController {
    
    @Autowired
    private ICitaService citaService;

    private Map<String, Object> response = new HashMap<>();
    private CitaDtoExtends cita;
    private HttpStatus status;


    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<CitaDtoExtends>> getAllCitas(){
        return new ResponseEntity<>(citaService.getAllCitas(), HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping("/detallesCita/{idCita}")
    public ResponseEntity<List<DetalleCitaDto>> getAllDetallesCita(@PathVariable Long idCita){
        return new ResponseEntity<>(citaService.getAllDetallesCita(idCita), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/fecha/{fechaAgenda}")
    public ResponseEntity<?> getCitasPorFechaAgenda(@PathVariable String fechaAgenda){
        response.clear();
        try{
            List<CitaDtoExtends> citas = citaService.getCitasPorFechaAgenda(fechaAgenda);
            response.put("mensaje", "Ok!");
            response.put("citas", citas);
            status = HttpStatus.OK;
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar buscar citas por fecha!");
            response.put("error", e.getMostSpecificCause().getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(response, status);
    }


    @ResponseBody
    @GetMapping("/veterinario/{idVeterinario}")
    public ResponseEntity<List<CitaDtoExtends>> getCitasPorVeterinario(@PathVariable Long idVeterinario){
        return new ResponseEntity<>(citaService.getCitasPorVeterinario(idVeterinario), HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping("/{idCita}")
    public ResponseEntity<CitaDtoExtends> getCitaPorId(@PathVariable Long idCita){
        return new ResponseEntity<>(citaService.getCitaPorId(idCita), HttpStatus.OK);
    }


    @ResponseBody
    @PostMapping("/{idVeterinario}")
    public ResponseEntity<?> crearCita(@Valid @RequestBody CitaServiciosArca citaDto,
            BindingResult result, @PathVariable Long idVeterinario){
        response.clear();
        if(!result.hasErrors()){
            try{
                cita = citaService.crearCita(citaDto, idVeterinario);
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
    @PutMapping("/{idCita}/{idVeterinario}")
    public ResponseEntity<?> modificarCita(@Valid @RequestBody CitaServiciosArca citaDto, BindingResult result,
                                           @PathVariable Long idCita, @PathVariable Long idVeterinario){
        response.clear();
        if(!result.hasErrors()){
            try{
                cita = citaService.modificarCita(citaDto, idCita, idVeterinario);
                if(cita != null){
                    response.put("mensaje", "cita actualizada con exito!");
                    response.put("cita", cita);
                    status = HttpStatus.CREATED;
                }else{
                    response.put("mensaje", "No existe la cita con id: "+idCita.toString()+" o no existe el veterinario con id: "+idVeterinario.toString());
                    status = HttpStatus.BAD_REQUEST;
                }
            }catch(DataAccessException e){
                response.put("mensaje", "Ha ocurrido un error en el servidor al intentar actualizar la cita! ");
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
    @DeleteMapping("/{idCita}")
    public ResponseEntity<?> eliminarCita(Long idCita) {
        response.clear();
        try{
            boolean eliminado = citaService.eliminarCita(idCita);
            if(eliminado){
                response.put("mensaje", "Cita eliminada con exito!");
                status = HttpStatus.OK;
            }else{
                response.put("mensaje", "No existe la cita con id: "+idCita.toString());
                status = HttpStatus.BAD_REQUEST;
            }
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar eliminar la cita! ");
            response.put("error", e.getMostSpecificCause().getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
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

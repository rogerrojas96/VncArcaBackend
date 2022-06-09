package com.vncarca.arcasys.donaciones.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vncarca.arcasys.donaciones.Dto.DonacionDto;
import com.vncarca.arcasys.donaciones.model.Donacion;
import com.vncarca.arcasys.donaciones.service.IDonacionService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Api(tags = "Donaciones", description = "Controlador para CRUD de donaciones")
@RestController
@RequestMapping("/donaciones")
public class DonacionController {
    
    @Autowired
    private IDonacionService donacionService;

    private Map<String, Object> response = new HashMap<>();
    private Donacion donacion;
    private HttpStatus status;



    @ResponseBody
    @PostMapping("/{idPersona}")
    public ResponseEntity<?> crearDonacion(
                @Valid @RequestBody DonacionDto donacionDto, BindingResult result, @PathVariable Long idPersona) {
        response.clear();
        if(!result.hasErrors()){
            try{
                donacion = donacionService.crearDonacion(donacionDto, idPersona);
                if(donacion != null){
                    response.put("mensaje", "Donación creada con exito!");
                    response.put("donacion", donacion);
                    status = HttpStatus.CREATED;
                }else{
                    response.put("mensaje", "No existe la persona con id: "+idPersona.toString());
                    status = HttpStatus.BAD_REQUEST;
                }
            }catch(DataAccessException e){
                response.put("mensaje", "Ha ocurrido un error en el servidor al intentar crear la Donación!");
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
    @DeleteMapping("/{idDonacion}")
    public ResponseEntity<?> eliminarDonacion(@PathVariable Long idDonacion) {
        response.clear();
        try{
            donacion = donacionService.eliminarDonacion(idDonacion);
            if(donacion != null){
                response.put("mensaje", "Donación eliminada con exito!");
                response.put("donacion", donacion);
                status = HttpStatus.OK;
            }else{
                response.put("mensaje", "No existe la donación con id: "+idDonacion.toString());
                status = HttpStatus.BAD_REQUEST;
            }
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar eliminar la donación! ");
            response.put("error", e.getMostSpecificCause().getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(response, status); 
    }

   
    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<List<Donacion>> getAllDonaciones() {
        return new ResponseEntity<>(donacionService.getAllDonaciones(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/all/{idPersona}")
    public ResponseEntity<?> getAllDonacionesPorPersona(@PathVariable Long idPersona) {
        response.clear();
        try{
            List<Donacion> donaciones = donacionService.getAllDonacionesPorPersona(idPersona);
            if(donaciones != null){
                response.put("mensaje", "Donaciones obtenidas con exito!");
                response.put("donaciones", donaciones);
                status = HttpStatus.OK;
            }else{
                response.put("mensaje", "No existe la persona con id: "+idPersona.toString());
                status = HttpStatus.BAD_REQUEST;
            }
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar buscar las adopciones! ");
            response.put("error", e.getMostSpecificCause().getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(response, status);
    }

    @ResponseBody
    @GetMapping("/{idDonacion}")
    public ResponseEntity<?> getDonacionPorId(@PathVariable Long idDonacion) {
        response.clear();
        try{
            donacion = donacionService.getDonacionPorId(idDonacion);
            if(donacion != null){
                response.put("mensaje", "Donación obtenida con exito!");
                response.put("donacion", donacion);
                status = HttpStatus.OK;
            }else{
                response.put("mensaje", "No existe la donación con id: "+idDonacion.toString());
                status = HttpStatus.BAD_REQUEST;
            }
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar buscar la adopcion! ");
            response.put("error", e.getMostSpecificCause().getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(response, status);
    }

    
    @ResponseBody
    @PutMapping("/{idPersona}/{idDonacion}/")
    public ResponseEntity<?> editarDonacion(@Valid @RequestBody DonacionDto donacionDto, 
            BindingResult result,  @PathVariable Long idPersona,  @PathVariable Long idDonacion) {
        response.clear();
        if(!result.hasErrors()){
            try{
                donacion = donacionService.editarDonacion(donacionDto, idPersona, idDonacion);
                if(donacion != null){
                    response.put("mensaje", "donación actualizada con exito!");
                    response.put("donacion", donacion);
                    status = HttpStatus.CREATED;
                }else{
                    response.put("mensaje", "No existe la persona con id: "+idPersona.toString()+" o no existe la donación con id: "+idDonacion.toString());
                    status = HttpStatus.BAD_REQUEST;
                }
            }catch(DataAccessException e){
                response.put("mensaje", "Ha ocurrido un error en el servidor al intentar actualizar la donación! ");
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

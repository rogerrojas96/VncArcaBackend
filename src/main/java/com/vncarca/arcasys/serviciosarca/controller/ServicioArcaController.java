package com.vncarca.arcasys.serviciosarca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.vncarca.arcasys.serviciosarca.dto.ServicioArcaDto;
import com.vncarca.arcasys.serviciosarca.model.ServicioArca;
import com.vncarca.arcasys.serviciosarca.service.IServicioArcaService;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "Servicios", description = "Controlador para CRUD de servicios ofrecidos por la fundación ARCA")
@RestController
@RequestMapping("/servicios")
public class ServicioArcaController {

    @Autowired
    private IServicioArcaService servicioArcaService;

    private Map<String, Object> response = new HashMap<>();
    private ServicioArca servicioArca;
    private HttpStatus status;


    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<ServicioArca>> getAllServiciosArca(){
        return new ResponseEntity<>(servicioArcaService.getAllServiciosArca(), HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneServicioArca(@PathVariable Long id){
        response.clear();
        try{
            servicioArca = servicioArcaService.getOneServicioArca(id);
            if(servicioArca == null){
                response.put("mensaje", "El Servicio con ID: ".concat(id.toString()).concat(" no existe en el servidor!"));
                status = HttpStatus.NOT_FOUND;
            }else{
                response.put("servicio", servicioArca);
                status = HttpStatus.OK;
            }
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar buscar el servicio con id: "+id.toString());
            response.put("error", e.getMostSpecificCause().getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(response, status);
    }


    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<?> crearServicioArca(
            @Valid @RequestBody ServicioArcaDto servicioArcaDto, BindingResult result){
        response.clear();
        if(!result.hasErrors()){
            try{
                servicioArca = servicioArcaService.crearServicioArca(servicioArcaDto);
                response.put("mensaje", "Servicio creado con exito!");
                response.put("servicio", servicioArca);
                status = HttpStatus.CREATED;
            }catch(DataAccessException e){
                response.put("mensaje", "Ha ocurrido un error en el servidor al intentar crear el servicio! ");
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
    @PutMapping("/{id}")
    public ResponseEntity<?> editarServicioArca(@Valid @RequestBody ServicioArcaDto servicioArcaDto, 
            BindingResult result, @PathVariable Long id){
        response.clear();
        if(!result.hasErrors()){
            try{
                servicioArca = servicioArcaService.editarServicioArca(servicioArcaDto, id);
                if(servicioArca != null){
                    response.put("mensaje", "Servicio actualizado con exito!");
                    response.put("servicio", servicioArca);
                    status = HttpStatus.CREATED;
                }else{
                    response.put("mensaje", "No existe el servicio con id: "+id.toString());
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

    
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarServicioArca(@PathVariable Long id){
        try{
            boolean eliminado = servicioArcaService.eliminarServicioArca(id);
            if(eliminado){
                response.put("mensaje", "Servicio eliminado con exito!");
                status = HttpStatus.OK;
            }else{
                response.put("mensaje", "No existe el servicio con id: "+id.toString());
                status = HttpStatus.BAD_REQUEST;
            }
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar eliminar el servicio! ");
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

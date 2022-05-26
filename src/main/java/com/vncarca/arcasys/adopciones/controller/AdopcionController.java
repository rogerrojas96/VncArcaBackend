package com.vncarca.arcasys.adopciones.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.vncarca.arcasys.adopciones.dto.AdopcionDto;
import com.vncarca.arcasys.adopciones.model.Adopcion;
import com.vncarca.arcasys.adopciones.service.IAdopcionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RequestMapping("/adopciones")

@RestController
public class AdopcionController {
   
    @Autowired
    private IAdopcionService adopcionService;



    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<?> getAllAdopciones() {
        return new ResponseEntity<List<Adopcion>>( adopcionService.getAllAdopciones(), HttpStatus.OK); 
    }


    @ResponseBody
    @GetMapping("/allbyci/{cedula}")
    public ResponseEntity<?> getAdopcionesPorCedulaAdoptante(@PathVariable String cedula){
        Map<String, Object> response = new HashMap<>();
        List<Adopcion> adopciones = null;
        try {
            adopciones = adopcionService.getAdopcionesPorCedulaAdoptante(cedula);
              
        } catch (DataAccessException e) {
            response.put("mensaje", "Ocurrio un error en el servidor al buscar Adopciones por CI: ".concat(cedula));
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (adopciones == null){
            response.put("mensaje", "No existen adopciones para el Adoptante con CI: ".concat(cedula));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Adopcion>>( adopciones, HttpStatus.OK);  
    }


    @ResponseBody
    @GetMapping("/allbyid/{id}")
    public ResponseEntity<?> getAdopcionesPorIdAdoptante(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        List<Adopcion> adopciones = null;
        try {
            adopciones = adopcionService.getAdopcionesPorIdAdoptante(id);
              
        } catch (DataAccessException e) {
            response.put("mensaje", "Ocurrio un error en el servidor al buscar Adopciones por Adoptante con ID: ".concat(id.toString()));
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (adopciones == null){
            response.put("mensaje", "No existen adopciones para el Adoptante con ID: ".concat(id.toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Adopcion>>( adopciones, HttpStatus.OK);  
    }


    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<?> etAdopcionPorId(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Adopcion adopcion = null;
        try {
            adopcion = adopcionService.getAdopcionPorId(id);
              
        } catch (DataAccessException e) {
            response.put("mensaje", "Ocurrio un error en el servidor al buscar adopción con ID: ".concat(id.toString()));
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (adopcion == null){
            response.put("mensaje", "No existe adopción con ID: ".concat(id.toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Adopcion>( adopcion, HttpStatus.OK);  
    }


    @PostMapping("/{idAdoptante}/{idAnimal}")
    public ResponseEntity<?> crearAdocion(@Valid @RequestBody AdopcionDto adopcionDto, 
            @PathVariable Long idAdoptante, @PathVariable Long idAnimal, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        Adopcion adopcion = null;

        if (result.hasErrors()) {
            response.put("errors", getErrors(result));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            adopcion = adopcionService.crearAdocion(adopcionDto, idAdoptante, idAnimal);
        } catch (DataAccessException e) {
            response.put("mensaje", "Ocurrio un error en el servidor al intentar crear la adopción!");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(adopcion == null){
            response.put("mensaje", "Error al buscar adoptante o animal o el animal ya ha sido adoptado!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("mensaje", "La adopcion se registro con exito!");
        response.put("adopcion", adopcion);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }



    @ResponseBody
    @PutMapping("/{idAdopcion}/{idAnimal}")
    public ResponseEntity<?> actualizarAdocion(@Valid @RequestBody AdopcionDto adopcionDto, 
                @PathVariable Long idAdopcion, @PathVariable Long idAnimal, BindingResult result){
            Map<String, Object> response = new HashMap<>();
            Adopcion adopcion = null;
    
            if (result.hasErrors()) {
                response.put("errors", getErrors(result));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            try {
                adopcion = adopcionService.actualizarAdocion(adopcionDto, idAdopcion, idAnimal);
            } catch (DataAccessException e) {
                response.put("mensaje", "Ocurrio un error en el servidor al intentar actualizar la adopción!");
                response.put("error", e.getMostSpecificCause().getMessage());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(adopcion == null){
                response.put("mensaje", "Error al intentar actualizar la adopcion o el animal ya ha sido adoptado!");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            response.put("mensaje", "Adopción actualizada con exito!");
            response.put("adopcion", adopcion);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @ResponseBody
    @DeleteMapping("/{idAdopcion}")
    public ResponseEntity<?> eliminarAdopcion(@PathVariable Long idAdopcion){
        Map<String, Object> response = new HashMap<>();
        try {
            boolean eliminado = adopcionService.eliminarAdopcion(idAdopcion);
            if(eliminado){
                response.put("mensaje", "Adopción eliminada con exito!");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Ocurrio un error en el servidor al intentar eliminar la adopción!");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La adopción no esta registrada!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    private List<String> getErrors(BindingResult result){
        return result.getFieldErrors().stream().map(err -> {
            return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
        }).collect(Collectors.toList());
    }
}

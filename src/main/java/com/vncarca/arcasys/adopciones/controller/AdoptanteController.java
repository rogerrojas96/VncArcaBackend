package com.vncarca.arcasys.adopciones.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.vncarca.arcasys.adopciones.dto.AdoptanteDto;
import com.vncarca.arcasys.adopciones.model.Adoptante;
import com.vncarca.arcasys.adopciones.service.IAdoptanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/adoptantes")
public class AdoptanteController {
    
    @Autowired
    private IAdoptanteService adoptanteService;


    @ResponseBody
    @GetMapping("/")
    public Page<Adoptante> getAllAdoptantes(@RequestParam(required = true) Integer page,
        @RequestParam(required = true) Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return adoptanteService.getAllAdoptantes(pageable);
    }


    @ResponseBody
    @GetMapping("/adoptanteci/{cedula}")
    public ResponseEntity<?> getAdoptantePorCedula(@PathVariable String cedula) {
        Map<String, Object> response = new HashMap<>();
        Adoptante adoptante = null;
        try {
            adoptante = adoptanteService.getAdoptantePorCedula(cedula);
              
        } catch (DataAccessException e) {
            response.put("mensaje", "Ocurrio un error en el servidor al buscar Adoptante con CI: ".concat(cedula));
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (adoptante == null){
            response.put("mensaje", "El Adoptante con CI: ".concat(cedula).concat(", no existe en el servidor!"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Adoptante>( adoptante, HttpStatus.OK);  
    }


    @ResponseBody
    @GetMapping("/{idAdoptante}")
    public ResponseEntity<?> getAdoptantePorId(@PathVariable Long idAdoptante) {
        Map<String, Object> response = new HashMap<>();
        Adoptante adoptante = null;
        try {
            adoptante = adoptanteService.getAdoptantePorId(idAdoptante);
              
        } catch (DataAccessException e) {
            response.put("mensaje", "Ocurrio un error en el servidor al buscar Adoptante con ID: ".concat(idAdoptante.toString()));
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (adoptante == null){
            response.put("mensaje", "El Adoptante con ID: ".concat(idAdoptante.toString()).concat(", no existe en el servidor!"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Adoptante>( adoptante, HttpStatus.OK);  
    }


    @PostMapping("/")
    public ResponseEntity<?> crearAdoptante(@Valid @RequestBody AdoptanteDto adoptanteDto, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        Adoptante adoptante = null;

        if (result.hasErrors()) {
            response.put("errors", getErrors(result));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            adoptante = adoptanteService.crearAdoptante(adoptanteDto);
        } catch (DataAccessException e) {
            response.put("mensaje", "Ocurrio un error en el servidor al intentar crear el Adoptante!");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(adoptante == null){
            response.put("mensaje", "El Adoptante con CI: ".concat(adoptanteDto.getCedula())
                    .concat(" ya se encuentra registrado!"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("mensaje", "Adoptante Registrado con exito!");
        response.put("adoptante", adoptante);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }



    @ResponseBody
    @PutMapping("/{idAdoptante}")
    public ResponseEntity<?> actualizarAdoptante(
        @Valid @RequestBody AdoptanteDto adoptanteDto, BindingResult result, @PathVariable Long idAdoptante){
            Map<String, Object> response = new HashMap<>();
            Adoptante adoptante = null;
    
            if (result.hasErrors()) {
                response.put("errors", getErrors(result));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            try {
                adoptante = adoptanteService.actualizarAdoptante(adoptanteDto, idAdoptante);
            } catch (DataAccessException e) {
                response.put("mensaje", "Ocurrio un error en el servidor al intentar actualizar el Adoptante!");
                response.put("error", e.getMostSpecificCause().getMessage());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(adoptante == null){
                response.put("mensaje", "El Adoptante con ID: ".concat(idAdoptante.toString())
                        .concat(" no encuentra registrado!"));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            response.put("mensaje", "Adoptante Actualizado con exito!");
            response.put("adoptante", adoptante);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @ResponseBody
    @DeleteMapping("/{idAdoptante}")
    public ResponseEntity<?> eliminarAdoptante(@PathVariable Long idAdoptante){
        Map<String, Object> response = new HashMap<>();
        try {
            boolean eliminado = adoptanteService.eliminarAdoptante(idAdoptante);
            if(eliminado){
                response.put("mensaje", "Adoptante eliminado con exito!");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Ocurrio un error en el servidor al intentar eliminar el Adoptante");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Adoptante no esta registrado!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }


    private List<String> getErrors(BindingResult result){
        return result.getFieldErrors().stream().map(err -> {
            return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
        }).collect(Collectors.toList());
    }
}

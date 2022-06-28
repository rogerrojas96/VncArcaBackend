package com.vncarca.arcasys.voluntarios.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vncarca.arcasys.voluntarios.model.Voluntario;
import com.vncarca.arcasys.voluntarios.model.VoluntarioDto;
import com.vncarca.arcasys.voluntarios.services.VoluntarioService;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Api(tags = "Voluntarios", description = "Controlador para CRUD de voluntarios")
@RestController
@RequestMapping("/voluntario")
public class VoluntarioController {
    @Autowired
    VoluntarioService voluntarioService;

    //EndPoint para listar voluntarios
    @ResponseBody
    @GetMapping("/page")
    public Page<Voluntario> getVoluntarios(@RequestParam(required = true) Integer page,
            @RequestParam(required = true) Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Voluntario> pageVoluntarios = voluntarioService.findAll(pageable);
        return pageVoluntarios;
    }
    
    //EndPoint para registrasr un voluntario
    @PostMapping(value="/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody VoluntarioDto voluntarioDto, BindingResult result) {

        Map<String, Object> response = new HashMap<>();
        Voluntario newVoluntario = null;

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            newVoluntario = voluntarioService.save(voluntarioDto);
            if (newVoluntario == null) {
                response.put("mensaje", "Error al crear, el Voluntario ya existe en la base de datos");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar Voluntario en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Voluntario guardado con exito");
        response.put("voluntario", newVoluntario);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    //EndPoint para actualizar voluntario
    @ResponseBody
    @PutMapping("/{idVoluntario}")
    public ResponseEntity<?> update(@Valid @RequestBody VoluntarioDto voluntarioDto, BindingResult result, @PathVariable Long idVoluntario) {

        Voluntario voluntarioUpdate = null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            voluntarioUpdate = voluntarioService.update(voluntarioDto, idVoluntario);
            if (voluntarioUpdate == null) {
                response.put("mensaje", "Error al actualizar, el Voluntario con ID: ".concat(idVoluntario.toString())
                        .concat(" no existe en el servidor"));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el Voluntario en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Voluntario actualizado vete exito");
        response.put("voluntario", voluntarioUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // EndPoint para buscar por Id
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Voluntario voluntario = null;
        Map<String, Object> response = new HashMap<>();

        try {
            voluntario = voluntarioService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error en la consulta de Voluntario en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (voluntario == null) {
            response.put("mensaje", "El Voluntario con ID: ".concat(id.toString()).concat(" no existe en el servidor"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Voluntario>(voluntario, HttpStatus.OK);
    }

    // EndPoint para buscar por Cedula
    @ResponseBody
    @GetMapping("/persona/{cedulaPersona}")
    public ResponseEntity<?> findByCedula(@PathVariable String cedulaPersona) {
        Voluntario voluntario = null;
        Map<String, Object> response = new HashMap<>();
        try {
            voluntario = voluntarioService.findByCedula(cedulaPersona);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error en la consulta de Voluntario en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (voluntario == null) {
            response.put("mensaje", "No existe el voluntario con CI:" .concat(cedulaPersona));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Voluntario>(voluntario, HttpStatus.OK);
    }


    //EndPoint para eliminar un voluntario
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            voluntarioService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el voluntario en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Voluntario eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}

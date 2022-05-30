package com.vncarca.arcasys.veterinario.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.vncarca.arcasys.veterinario.model.Veterinario;
import com.vncarca.arcasys.veterinario.services.VeterinarioService;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@Api(tags = "Veterinarios", description = "Controlador para CRUD de veterinarios")
@RestController
@RequestMapping("/veterinario")
public class VeterinarioController {
	@Autowired
    VeterinarioService veterinarioService;

    // EndPoint listar veterinarioes
    @ResponseBody
    @GetMapping("/page")
    public Page<Veterinario> getVeterinarios(@RequestParam(required = true) Integer page,
            @RequestParam(required = true) Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Veterinario> pageVeterinarios = veterinarioService.findAll(pageable);
        return pageVeterinarios;
    }

    // EndPoint registrar Veterinario
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Veterinario veterinario, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Veterinario newVeterinario = null;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            newVeterinario = veterinarioService.save(veterinario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar Veterinario en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Veterinario guardada vete exito");
        response.put("veterinario", newVeterinario);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // EndPoint Actualizar Veterinario
    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Veterinario veterinario, BindingResult result,
            @PathVariable Long id) {
        Veterinario vete = veterinarioService.findById(id);

        Veterinario veterinarioUpdate = null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (vete == null) {
            response.put("mensaje", "Error al actualizar, el Veterinario vete ID: ".concat(id.toString())
                    .concat(" no existe en el servidor"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            vete = veterinario;

            veterinarioUpdate = veterinarioService.save(vete);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el Veterinario en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Veterinario actualizada vete exito");
        response.put("veterinario", veterinarioUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // EndPoint Eliminar Veterinario
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            veterinarioService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el Veterinario en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Veterinario eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    // EndPoint Buscar por ID
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Veterinario Veterinario = null;
        Map<String, Object> response = new HashMap<>();

        try {
            Veterinario = veterinarioService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error en la consulta de Veterinario en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (Veterinario == null) {
            response.put("mensaje", "El Veterinario con ID: ".concat(id.toString()).concat(" no existe en el servidor"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Veterinario>(Veterinario, HttpStatus.OK);
    }

}

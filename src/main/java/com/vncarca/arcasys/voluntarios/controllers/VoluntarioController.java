package com.vncarca.arcasys.voluntarios.controllers;

import com.vncarca.arcasys.voluntarios.model.VoluntarioDto;
import com.vncarca.arcasys.voluntarios.model.VoluntarioDtoExtends;
import com.vncarca.arcasys.voluntarios.services.VoluntarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Api(tags = "Voluntarios", description = "Controlador para CRUD de voluntarios")
@RestController
@RequestMapping("/voluntario")
public class VoluntarioController {
    @Autowired
    VoluntarioService voluntarioService;
    
    //EndPoint para listar voluntarios
    @ResponseBody
    @GetMapping("/page")
    public Page<VoluntarioDtoExtends> getVoluntarios(@RequestParam(required = true) Integer page,
                                                     @RequestParam(required = true) Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VoluntarioDtoExtends> pageVoluntarios = voluntarioService.findAll(pageable);
        return pageVoluntarios;
    }
    
    //EndPoint para registrasr un voluntario
    @PostMapping(value="/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody VoluntarioDto voluntarioDto, BindingResult result) {
    
        Map<String, Object> response = new HashMap<>();
        VoluntarioDtoExtends newVoluntario = null;

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
                response.put("mensaje", "Error al crear, el VoluntarioDtoExtends ya existe en la base de datos");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar VoluntarioDtoExtends en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "VoluntarioDtoExtends guardado con exito");
        response.put("voluntario", newVoluntario);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    //EndPoint para actualizar voluntario
    @ResponseBody
    @PutMapping("/{idVoluntario}")
    public ResponseEntity<?> update(@Valid @RequestBody VoluntarioDto voluntarioDto, BindingResult result, @PathVariable Long idVoluntario) {
    
        VoluntarioDtoExtends voluntarioUpdate = null;

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
                response.put("mensaje",
                        "Error al actualizar, el VoluntarioDtoExtends con ID: ".concat(idVoluntario.toString())
                                .concat(" no existe en el servidor"));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el VoluntarioDtoExtends en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "VoluntarioDtoExtends actualizado vete exito");
        response.put("voluntario", voluntarioUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // EndPoint para buscar por Id
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        VoluntarioDtoExtends voluntario = null;
        Map<String, Object> response = new HashMap<>();

        try {
            voluntario = voluntarioService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error en la consulta de VoluntarioDtoExtends en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (voluntario == null) {
            response.put("mensaje", "El VoluntarioDtoExtends con ID: ".concat(id.toString()).concat(" no existe en el" +
                    " " +
                    "servidor"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VoluntarioDtoExtends>(voluntario, HttpStatus.OK);
    }

    // EndPoint para buscar por Cedula
    @ResponseBody
    @GetMapping("/persona/{cedulaPersona}")
    public ResponseEntity<?> findByCedula(@PathVariable String cedulaPersona) {
        VoluntarioDtoExtends voluntario = null;
        Map<String, Object> response = new HashMap<>();
        try {
            voluntario = voluntarioService.findByCedula(cedulaPersona);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error en la consulta de VoluntarioDtoExtends en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (voluntario == null) {
            response.put("mensaje", "No existe el voluntario con CI:" .concat(cedulaPersona));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VoluntarioDtoExtends>(voluntario, HttpStatus.OK);
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
        response.put("mensaje", "VoluntarioDtoExtends eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}

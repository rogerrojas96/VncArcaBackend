package com.vncarca.arcasys.carnetVacunacion.vacuna.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.vncarca.arcasys.carnetVacunacion.vacuna.model.VacunaDTO;
import com.vncarca.arcasys.carnetVacunacion.vacuna.services.VacunaServices;

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
@Api(tags = "Vacunas", description = "Controlador para CRUD de vacunas")
@RestController()
@RequestMapping("/vacunas")
public class VacunaController {
	@Autowired
    VacunaServices vacunaService;

    // EndPoint listar vacunas
    @ResponseBody
    @GetMapping("/page")
    public Page<VacunaDTO> getVacunas(@RequestParam(required = true) Integer page,
                                      @RequestParam(required = true) Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VacunaDTO> pageVacunas = vacunaService.findAll(pageable);
        return pageVacunas;
    }

	@GetMapping("/")
	public List<VacunaDTO> getVacunas(){
		 return vacunaService.findAll();
	}
    
	// EndPoint registrar Vacuna
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody VacunaDTO vacuna, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        VacunaDTO newVacuna = null;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            newVacuna = vacunaService.save(vacuna);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar Vacuna en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Vacuna guardada vacu exito");
        response.put("vacuna", newVacuna);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // EndPoint Actualizar Vacuna
    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody VacunaDTO vacuna, BindingResult result,
            @PathVariable Long id) {
        VacunaDTO vacu = vacunaService.findById(id);

        VacunaDTO vacunaUpdate = null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (vacu == null) {
            response.put("mensaje", "Error al actualizar, el Vacuna vacu ID: ".concat(id.toString())
                    .concat(" no existe en el servidor"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            vacu = vacuna;

            vacunaUpdate = vacunaService.save(vacu);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el Vacuna en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Vacuna actualizada vacu exito");
        response.put("vacuna", vacunaUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // EndPoint Eliminar Vacuna
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            vacunaService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el Vacuna en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Vacuna eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    // EndPoint Buscar por ID
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        VacunaDTO Vacuna = null;
        Map<String, Object> response = new HashMap<>();

        try {
            Vacuna = vacunaService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error en la consulta de Vacuna en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (Vacuna == null) {
            response.put("mensaje", "El Vacuna con ID: ".concat(id.toString()).concat(" no existe en el servidor"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VacunaDTO>(Vacuna, HttpStatus.OK);
    }

}

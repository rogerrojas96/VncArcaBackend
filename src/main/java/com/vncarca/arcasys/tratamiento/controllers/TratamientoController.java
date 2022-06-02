package com.vncarca.arcasys.tratamiento.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import javax.validation.Valid;


import com.vncarca.arcasys.tratamiento.model.Tratamiento;
import com.vncarca.arcasys.tratamiento.services.TratamientoService;


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


@Api(tags = "Tratamientos", description = "Controlador para CRUD tratamientos")
@RestController
@RequestMapping("/tratamientos")
public class TratamientoController {
    @Autowired
	TratamientoService tratamientoService;


    
	 // EndPoint listar Tratamientos
	 @ResponseBody
	 @GetMapping("/page")
	 public Page<Tratamiento> getTratamientos(@RequestParam(required = true) Integer page,
			 @RequestParam(required = true) Integer size) {
		 Pageable pageable = PageRequest.of(page, size);
		 Page<Tratamiento> pageTratamientos = tratamientoService.findAll(pageable);
		 return pageTratamientos;
	 }
	 @GetMapping("/")
	 public List<Tratamiento> getTratamientos(){
		  return tratamientoService.findAll();
	 }
 
	 // EndPoint registrar Tratamiento
	 @PostMapping("/")
	 @ResponseStatus(HttpStatus.CREATED)
	 public ResponseEntity<?> create(@Valid @RequestBody Tratamiento tratamiento, BindingResult result) {
		 Map<String, Object> response = new HashMap<>();
		 Tratamiento newTratamiento = null;
 
		 if (result.hasErrors()) {
			 List<String> errors = result.getFieldErrors().stream().map(err -> {
				 return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			 }).collect(Collectors.toList());
			 response.put("errors", errors);
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		 }
		 try {
			 newTratamiento = tratamientoService.save(tratamiento);
		 } catch (DataAccessException e) {
			 response.put("mensaje", "Error al guardar Tratamiento en el servidor");
			 response.put("error", e.getMostSpecificCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 response.put("mensaje", "Tratamiento guardada fichaCli exito");
		 response.put("tratamiento: ", newTratamiento);
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	 }
 
	 // EndPoint Actualizar Tratamiento
	 @ResponseBody
	 @PutMapping("/{id}")
	 public ResponseEntity<?> update(@Valid @RequestBody Tratamiento tratamiento, BindingResult result,
			 @PathVariable Long id) {
		 Tratamiento tratamiento2 = tratamientoService.findById(id);
 
		 Tratamiento tratamientoUpdate = null;
 
		 Map<String, Object> response = new HashMap<>();
		 if (result.hasErrors()) {
			 List<String> errors = result.getFieldErrors().stream().map(err -> {
				 return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			 }).collect(Collectors.toList());
			 response.put("errors", errors);
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		 }
 
		 if (tratamiento2 == null) {
			 response.put("mensaje", "Error al actualizar, el Tratamiento tratamiento2 ID: ".concat(id.toString())
					 .concat(" no existe en el servidor"));
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		 }
		 try {
			 tratamiento2 = tratamiento;
 
			 tratamientoUpdate = tratamientoService.save(tratamiento2);
		 } catch (DataAccessException e) {
			 response.put("mensaje", "Error al actualizar el Tratamiento en el servidor");
			 response.put("error", e.getMostSpecificCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 response.put("mensaje", "Tratamiento actualizada tratamiento2 exito");
		 response.put("tratamiento", tratamientoUpdate);
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	 }
 
	 // EndPoint Eliminar Tratamiento
	 @ResponseBody
	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> delete(@PathVariable Long id) {
		 Map<String, Object> response = new HashMap<>();
		 try {
			 tratamientoService.delete(id);
		 } catch (DataAccessException e) {
			 response.put("mensaje", "Error al eliminar el tratamiento en el servidor");
			 response.put("error", e.getMostSpecificCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 response.put("mensaje", "Tratamiento eliminado con exito");
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	 }
 
	 // EndPoint Buscar por ID
	 @ResponseBody
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getById(@PathVariable Long id) {
		 Tratamiento Tratamiento = null;
		 Map<String, Object> response = new HashMap<>();
 
		 try {
			 Tratamiento = tratamientoService.findById(id);
		 } catch (DataAccessException e) {
			 response.put("mensaje", "Error en la consulta de Tratamiento en el servidor");
			 response.put("error", e.getMostSpecificCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 if (Tratamiento == null) {
			 response.put("mensaje", "El Tratamiento con ID: ".concat(id.toString()).concat(" no existe en el servidor"));
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity<Tratamiento>(Tratamiento, HttpStatus.OK);
	 }
 
}

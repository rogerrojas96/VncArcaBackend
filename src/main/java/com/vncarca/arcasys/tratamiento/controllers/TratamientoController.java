package com.vncarca.arcasys.tratamiento.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import javax.validation.Valid;

import com.vncarca.arcasys.fichaclinica.services.FichaClinicaService;
import com.vncarca.arcasys.tratamiento.model.Tratamiento;
import com.vncarca.arcasys.tratamiento.model.TratamientoDto;
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
	@Autowired
	FichaClinicaService fichaClinicaService;

    
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
	 @PostMapping("/{idFicha}")
	 @ResponseStatus(HttpStatus.CREATED)
	 public ResponseEntity<?> create(@Valid @RequestBody TratamientoDto tratamientoDto, BindingResult result, @PathVariable Long idFicha) {
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
			 newTratamiento = tratamientoService.save(tratamientoDto, idFicha);
			 if(newTratamiento !=null){
				response.put("mensaje", "Creado con exito");
				response.put("Tratamiento", newTratamiento);
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			 }
			 response.put("mensaje", "No existe una ficha clinica con id: "+ idFicha.toString());
			 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		 } catch (DataAccessException e) {
			 response.put("mensaje", "Error al guardar Tratamiento en el servidor");
			 response.put("error", e.getMostSpecificCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
 
	 // EndPoint Actualizar Tratamiento
	 @ResponseBody
	 @PutMapping("/{idTratamiento}/{idFicha}")
	 public ResponseEntity<?> update(@Valid @RequestBody TratamientoDto tratamientoDto, BindingResult result,
			 @PathVariable Long idTratamiento, @PathVariable Long idFicha) {
		 Tratamiento tratamientoUpdate = null;
 
		 Map<String, Object> response = new HashMap<>();
		 if (result.hasErrors()) {
			 List<String> errors = result.getFieldErrors().stream().map(err -> {
				 return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			 }).collect(Collectors.toList());
			 response.put("errors", errors);
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		 }
		 try {
			 tratamientoUpdate = tratamientoService.update(tratamientoDto, idTratamiento, idFicha);
			if(tratamientoUpdate != null){
				response.put("mensaje", "Actualizado con exito");
			  	response.put("Tratamiento", tratamientoUpdate);
			 	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			}else{
				response.put("Mensaje", "Id de tratamiento o ficha clinica incorrectos");
			 	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		 } catch (DataAccessException e) {
			 response.put("mensaje", "Error al actualizar el Tratamiento en el servidor");
			 response.put("error", e.getMostSpecificCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
 
	 // EndPoint Eliminar Tratamiento
	 @ResponseBody
	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> delete(@PathVariable Long id) {
		 Map<String, Object> response = new HashMap<>();
		 
		 try {
			 boolean isEliminado = tratamientoService.delete(id);
			 if(isEliminado){
				response.put("mensaje", "Eliminado con exito");
			 	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}else{
				response.put("Mensaje", "Id de tratamiento incorrecto");
			 	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		 } catch (DataAccessException e) {
			 response.put("mensaje", "Error al eliminar el tratamiento en el servidor");
			 response.put("error", e.getMostSpecificCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
 
	 // EndPoint Buscar por ID
	 @ResponseBody
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getById(@PathVariable Long id) {
		 Tratamiento tratamiento = null;
		 Map<String, Object> response = new HashMap<>();
 
		 try {
			 tratamiento = tratamientoService.findById(id);
			 if(tratamiento != null){
			  	response.put("Tratamiento", tratamiento);
			 	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}else{
				response.put("Mensaje", "Id de tratamiento incorrecto");
			 	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		 } catch (DataAccessException e) {
			 response.put("mensaje", "Error en la consulta de Tratamiento en el servidor");
			 response.put("error", e.getMostSpecificCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }

	 // EndPoint Buscar por ID de Ficha Clinica
	 @ResponseBody
	 @GetMapping("/ficha/{idFichaClinica}")
	 public ResponseEntity<?> findByFichaClinica(@PathVariable Long idFichaClinica){
		List<Tratamiento> tratamientos = null;
		Map<String, Object> response = new HashMap<>();
		try {
			tratamientos = tratamientoService.findByFichaClinica(idFichaClinica);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en la consulta de Tratamiento en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (tratamientos == null) {
			response.put("mensaje", "El Tratamiento con ID: ".concat(idFichaClinica.toString()).concat(" no existe en el servidor"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(tratamientos, HttpStatus.OK);
	 }
}

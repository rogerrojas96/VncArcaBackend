package com.vncarca.arcasys.fichaclinica.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.vncarca.arcasys.fichaclinica.model.FichaClinica;
import com.vncarca.arcasys.fichaclinica.services.FichaClinicaService;

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

@Api(tags = "Fichas Clínicas", description = "Controlador para CRUD de fichas clínicas")
@RestController
@RequestMapping("/fichas-clinicas")
public class FichaClinicaController {
	@Autowired
	FichaClinicaService fichaClinicaService;

	 // EndPoint listar fichaClinicaes
	 @ResponseBody
	 @GetMapping("/page")
	 public Page<FichaClinica> getFichaClinicas(@RequestParam(required = true) Integer page,
			 @RequestParam(required = true) Integer size) {
		 Pageable pageable = PageRequest.of(page, size);
		 Page<FichaClinica> pageFichaClinicas = fichaClinicaService.findAll(pageable);
		 return pageFichaClinicas;
	 }
 
	 // EndPoint registrar FichaClinica
	 @PostMapping("/")
	 @ResponseStatus(HttpStatus.CREATED)
	 public ResponseEntity<?> create(@Valid @RequestBody FichaClinica fichaClinica, BindingResult result) {
		 Map<String, Object> response = new HashMap<>();
		 FichaClinica newFichaClinica = null;
 
		 if (result.hasErrors()) {
			 List<String> errors = result.getFieldErrors().stream().map(err -> {
				 return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			 }).collect(Collectors.toList());
			 response.put("errors", errors);
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		 }
		 try {
			 newFichaClinica = fichaClinicaService.save(fichaClinica);
		 } catch (DataAccessException e) {
			 response.put("mensaje", "Error al guardar FichaClinica en el servidor");
			 response.put("error", e.getMostSpecificCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 response.put("mensaje", "FichaClinica guardada fichaCli exito");
		 response.put("fichaClinica", newFichaClinica);
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	 }
 
	 // EndPoint Actualizar FichaClinica
	 @ResponseBody
	 @PutMapping("/{id}")
	 public ResponseEntity<?> update(@Valid @RequestBody FichaClinica fichaClinica, BindingResult result,
			 @PathVariable Long id) {
		 FichaClinica fichaCli = fichaClinicaService.findById(id);
 
		 FichaClinica fichaClinicaUpdate = null;
 
		 Map<String, Object> response = new HashMap<>();
		 if (result.hasErrors()) {
			 List<String> errors = result.getFieldErrors().stream().map(err -> {
				 return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			 }).collect(Collectors.toList());
			 response.put("errors", errors);
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		 }
 
		 if (fichaCli == null) {
			 response.put("mensaje", "Error al actualizar, el FichaClinica fichaCli ID: ".concat(id.toString())
					 .concat(" no existe en el servidor"));
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		 }
		 try {
			 fichaCli = fichaClinica;
 
			 fichaClinicaUpdate = fichaClinicaService.save(fichaCli);
		 } catch (DataAccessException e) {
			 response.put("mensaje", "Error al actualizar el FichaClinica en el servidor");
			 response.put("error", e.getMostSpecificCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 response.put("mensaje", "FichaClinica actualizada fichaCli exito");
		 response.put("fichaClinica", fichaClinicaUpdate);
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	 }
 
	 // EndPoint Eliminar FichaClinica
	 @ResponseBody
	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> delete(@PathVariable Long id) {
		 Map<String, Object> response = new HashMap<>();
		 try {
			 fichaClinicaService.delete(id);
		 } catch (DataAccessException e) {
			 response.put("mensaje", "Error al eliminar el FichaClinica en el servidor");
			 response.put("error", e.getMostSpecificCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 response.put("mensaje", "FichaClinica eliminado con exito");
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	 }
 
	 // EndPoint Buscar por ID
	 @ResponseBody
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getById(@PathVariable Long id) {
		 FichaClinica FichaClinica = null;
		 Map<String, Object> response = new HashMap<>();
 
		 try {
			 FichaClinica = fichaClinicaService.findById(id);
		 } catch (DataAccessException e) {
			 response.put("mensaje", "Error en la consulta de FichaClinica en el servidor");
			 response.put("error", e.getMostSpecificCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 if (FichaClinica == null) {
			 response.put("mensaje", "El FichaClinica con ID: ".concat(id.toString()).concat(" no existe en el servidor"));
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity<FichaClinica>(FichaClinica, HttpStatus.OK);
	 }
 
}

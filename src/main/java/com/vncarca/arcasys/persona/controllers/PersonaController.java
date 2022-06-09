package com.vncarca.arcasys.persona.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.services.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

@Api(tags = "Personas", description = "Controlador para CRUD de personas")
@RestController
@RequestMapping("/personas")
public class PersonaController {
	@Autowired
	PersonaService personaService;

	// EndPoint listar personas por pagina
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	@GetMapping("/page")
	public Page<Persona> getPersonas(@RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Persona> pagePersonas = personaService.findAll(pageable);
		return pagePersonas;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/")
	public List<Persona> getPersonas(){
		 return personaService.findAll();
	}

	// EndPoint registrar Persona
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Persona persona, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		Persona newPersona = null;

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			newPersona = personaService.save(persona);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al guardar Persona en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Persona guardada perso exito");
		response.put("persona", newPersona);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// EndPoint Actualizar Persona
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Persona persona, BindingResult result,
			@PathVariable Long id) {
		Persona perso = personaService.findById(id);

		Persona personaUpdate = null;

		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (perso == null) {
			response.put("mensaje", "Error al actualizar, el Persona perso ID: ".concat(id.toString())
					.concat(" no existe en el servidor"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			perso = persona;

			personaUpdate = personaService.save(perso);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Persona en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Persona actualizada perso exito");
		response.put("persona", personaUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// EndPoint Eliminar Persona
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			personaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el Persona en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Persona eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// EndPoint Buscar por ID
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Persona Persona = null;
		Map<String, Object> response = new HashMap<>();

		try {
			Persona = personaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en la consulta de Persona en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (Persona == null) {
			response.put("mensaje", "El Persona con ID: ".concat(id.toString()).concat(" no existe en el servidor"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Persona>(Persona, HttpStatus.OK);
	}

}

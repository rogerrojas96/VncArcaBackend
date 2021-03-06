package com.vncarca.arcasys.veterinario.controllers;

import com.vncarca.arcasys.veterinario.model.VeterinarioDTO;
import com.vncarca.arcasys.veterinario.services.VeterinarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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

@Api(tags = "Veterinarios", description = "Controlador para CRUD de veterinarios")
@RestController
@RequestMapping("/veterinario")
public class VeterinarioController {
	@Autowired
	VeterinarioService veterinarioService;

	// EndPoint listar veterinarioes
	@ResponseBody
	@GetMapping("/page")
	public Page<VeterinarioDTO> getVeterinarios(@RequestParam(required = true) Integer page,
												@RequestParam(required = true) Integer size, @RequestParam(required = false, defaultValue = "") String cedula) {
		Pageable pageable = PageRequest.of(page, size);
		Page<VeterinarioDTO> pageVeterinarios = null;
		if (cedula.isEmpty() || cedula == null) {
			pageVeterinarios = veterinarioService.findAll(pageable);
		} else {
			pageVeterinarios = veterinarioService.findByCedula(pageable, cedula);
		}
		return pageVeterinarios;
	}

	@ResponseBody
	@GetMapping("/findAll")
	public List<VeterinarioDTO> getAllVeterinarios() {
		return veterinarioService.findAll();
	}

	// EndPoint registrar Veterinario
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody VeterinarioDTO veterinario, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		VeterinarioDTO newVeterinario = null;

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
	public ResponseEntity<?> update(@Valid @RequestBody VeterinarioDTO veterinario, BindingResult result,
			@PathVariable Long id) {
		VeterinarioDTO vete = veterinarioService.findById(id);

		VeterinarioDTO veterinarioUpdate = null;

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
			response.put("mensaje", "Veterinario eliminado con exito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException("??No existe Veterinario con id: " + id.toString() + " !",
					e.getExpectedSize());
		}
	}

	// EndPoint Buscar por ID
	@ResponseBody
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		VeterinarioDTO Veterinario = null;
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
		return new ResponseEntity<VeterinarioDTO>(Veterinario, HttpStatus.OK);
	}


}

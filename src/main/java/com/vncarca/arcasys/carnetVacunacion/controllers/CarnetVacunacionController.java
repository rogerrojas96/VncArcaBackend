/**
* Created by Roy Morocho.
* User: steve
* Date: 10 jun 2022
* Time: 15:06:12
*/
package com.vncarca.arcasys.carnetVacunacion.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
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

import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacion;
import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacionDTO;
import com.vncarca.arcasys.carnetVacunacion.services.CarnetVacunacionService;
import io.swagger.annotations.Api;

@Api(tags = "Carnets de Vacunación", description = "Controlador para CRUD de Carnets de vacunación")
@RestController
@RequestMapping("/carnet-vacunacion")
public class CarnetVacunacionController {
	@Autowired
	CarnetVacunacionService carnetVacunacionService;

	// EndPoint listar carnetsVacunaciones
	@ResponseBody
	@GetMapping("/page")
	public Page<CarnetVacunacion> getCarnetsVacunaciones(@RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<CarnetVacunacion> pageCarnetsVacunaciones = carnetVacunacionService.findAll(pageable);
		return pageCarnetsVacunaciones;
	}

	@GetMapping("/")
	public List<CarnetVacunacion> getcarnetsVacunaciones() {
		return carnetVacunacionService.findAll();
	}

	@GetMapping("/findByAnimalId/{id}")
	public List<CarnetVacunacionDTO> getFichasClinicasByAnimalId(@RequestParam(required = true) Long id) {
		return carnetVacunacionService.findByanimalId(id);
	}

	// EndPoint registrar CarnetVacunacion
	@PostMapping("/")
	@SendTo()
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody CarnetVacunacion carnetVacunacion, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		CarnetVacunacion newcarnetVacunacion = null;

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			newcarnetVacunacion = carnetVacunacionService.save(carnetVacunacion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al guardar CarnetVacunacion en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "CarnetVacunacion guardada con exito");
		response.put("carnetVacunacion", newcarnetVacunacion);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// EndPoint Actualizar CarnetVacunacion
	@ResponseBody
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody CarnetVacunacion carnetVacunacion, BindingResult result,
			@PathVariable Long id) {
		CarnetVacunacion carnetVac = carnetVacunacionService.findById(id);
		CarnetVacunacion carnetVacunacionUpdate = null;
		if (!Objects.equals(id, carnetVacunacion.getId())) {
			throw new IllegalArgumentException("IDs no coinciden");
		}
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (carnetVac == null) {
			response.put("mensaje", "Error al actualizar, el CarnetVacunacion carnetVac ID: ".concat(id.toString())
					.concat(" no existe en el servidor"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			carnetVac = carnetVacunacion;
			carnetVacunacionUpdate = carnetVacunacionService.save(carnetVac);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el CarnetVacunacion en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "CarnetVacunacion actualizada carnetVac exito");
		response.put("carnetVacunacion", carnetVacunacionUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// EndPoint Eliminar CarnetVacunacion
	@ResponseBody
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			carnetVacunacionService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el CarnetVacunacion en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "CarnetVacunacion eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// EndPoint Buscar por ID
	@ResponseBody
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		CarnetVacunacion CarnetVacunacion = null;
		Map<String, Object> response = new HashMap<>();

		try {
			CarnetVacunacion = carnetVacunacionService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en la consulta de CarnetVacunacion en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (CarnetVacunacion == null) {
			response.put("mensaje", "El CarnetVacunacion con ID: ".concat(id.toString()).concat(" no existe en el servidor"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CarnetVacunacion>(CarnetVacunacion, HttpStatus.OK);
	}

}
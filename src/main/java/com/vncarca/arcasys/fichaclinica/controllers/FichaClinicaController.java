package com.vncarca.arcasys.fichaclinica.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.vncarca.arcasys.fichaclinica.model.FichaClinica;
import com.vncarca.arcasys.fichaclinica.model.FichaClinicaDTO;
import com.vncarca.arcasys.fichaclinica.model.FichaClinicaRequestDTO;
import com.vncarca.arcasys.fichaclinica.services.FichaClinicaService;
import com.vncarca.arcasys.responses.CustomResponseEntity;

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
	public Page<FichaClinicaDTO> getFichasClinicas(@RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer size,
			@RequestParam(required = false, defaultValue = "") String tipoPaciente) {
		Pageable pageable = PageRequest.of(page, size);
		return (tipoPaciente == null || tipoPaciente.isEmpty()) ? fichaClinicaService.findAll(pageable)
				: fichaClinicaService.findBytipoPacienteContaining(pageable, tipoPaciente.toUpperCase());
	}

	// @GetMapping("/")
	// public List<FichaClinicaDTO> getFichasClinicas() {

	// return fichaClinicaService.findAll();
	// }

	@GetMapping("/")
	public List<FichaClinicaDTO> getFichasClinicas() {
		return fichaClinicaService.findAll();
	}

	@GetMapping("/findByAnimalId/{id}")
	public List<FichaClinicaDTO> getFichasClinicasByAnimalId(@RequestParam(required = true) Long id) {
		return fichaClinicaService.findByanimalId(id);
	}

	// EndPoint registrar FichaClinica
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody FichaClinicaRequestDTO fichaClinicaRequestDTO) {
		try {
			FichaClinicaDTO fichaClinicaDTO = fichaClinicaService.save(fichaClinicaRequestDTO);
			return new CustomResponseEntity(HttpStatus.CREATED, "Ficha clínica guardada con exito", fichaClinicaDTO)
					.response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al guardar Ficha clinínica en el servidor", e) {
			};
		}
	}

	// EndPoint Actualizar FichaClinica
	@ResponseBody
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody FichaClinicaRequestDTO fichaClinicaRequestDTO,
			@PathVariable Long id) {
		FichaClinicaDTO animalToUpdate = fichaClinicaService.findById(id);
		if (!Objects.equals(id, fichaClinicaRequestDTO.getId())) {
			throw new IllegalArgumentException("El id {" + id + "} no coincide con el id de la ficha Clinica");
		}

		try {
//			animalToUpdate = fichaClinicaService.convertRequestToEntity(fichaClinicaRequestDTO);
			FichaClinicaDTO fichaUpdate = fichaClinicaService.update(fichaClinicaRequestDTO);
			return new CustomResponseEntity(HttpStatus.CREATED, "Ficha clínica actualizada con exito",
					fichaUpdate).response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al actualizar el Animal en el servidor", e) {
			};
		}

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
		FichaClinicaDTO fichaClinica = null;
		Map<String, Object> response = new HashMap<>();

		try {
			fichaClinica = fichaClinicaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en la consulta de FichaClinica en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (fichaClinica == null) {
			response.put("mensaje", "El FichaClinica con ID: ".concat(id.toString()).concat(" no existe en el servidor"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FichaClinicaDTO>(fichaClinica, HttpStatus.OK);
	}

}

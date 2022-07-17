package com.vncarca.arcasys.persona.controllers;

import com.vncarca.arcasys.persona.model.PersonaDto;
import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import com.vncarca.arcasys.persona.services.PersonaService;
import com.vncarca.arcasys.responses.CustomResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Tag(name = "Personas", description = "Controlador para CRUD de personas")
@RestController
@RequestMapping("/personas")
public class PersonaController {
	@Autowired
	PersonaService personaService;

	// EndPoint listar personas por pagina
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	@GetMapping("/page")
	public Page<PersonaDtoExtends> getPersonas(@RequestParam Integer page,
	                                           @RequestParam Integer size, @RequestParam(required = false, defaultValue = "") String cedula) {
		Pageable pageable = PageRequest.of(page, size);
		Page<PersonaDtoExtends> pagePersonas;
		if (cedula.isEmpty()) {
			pagePersonas = personaService.findAll(pageable);
		} else {
			pagePersonas = personaService.findByCedula(pageable, cedula);
		}
		return pagePersonas;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/")
	public List<PersonaDtoExtends> getPersonas() {
		return personaService.findAll();
	}

	// EndPoint registrar Persona
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody PersonaDtoExtends persona) {
		try {
			PersonaDtoExtends newPersona = personaService.save(persona);
			return new CustomResponseEntity(HttpStatus.CREATED, "Persona guardada con exito", newPersona).response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al guardar persona en el servidor", e) {
			};
		}
	}

	// EndPoint Actualizar Persona
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody PersonaDtoExtends persona,
			@PathVariable Long id) {
		PersonaDtoExtends personaToUpdate = personaService.findById(id);
		if (!Objects.equals(id, persona.getId())) {
			throw new IllegalArgumentException("El id {" + id + "} no coincide con el id de la persona");
		}
		try {
			personaToUpdate = persona;

			PersonaDtoExtends personaUpdate = personaService.save(personaToUpdate);
			return new CustomResponseEntity(HttpStatus.CREATED, "Persona actualizada con exito", personaUpdate).response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al actualizar la persona en el servidor", e) {
			};
		}
	}

	// EndPoint Eliminar Persona
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			personaService.delete(id);
			return new CustomResponseEntity(HttpStatus.OK, "Persona eliminada con exito").response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al eliminar el Persona en el servidor", e) {
			};
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		try {
			PersonaDto persona = personaService.findById(id);
			return new ResponseEntity<>(persona, HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DataAccessException("Error en la consulta de la persona en el servidor", e) {
			};
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	@GetMapping("/findByCedula/{cedula}")
	public ResponseEntity<?> findByCedula(@PathVariable String cedula) {
		try {
			PersonaDto persona = personaService.findByCedula(cedula);
			return new ResponseEntity<>(persona, HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DataAccessException("Error en la consulta de la persona en el servidor", e) {
			};
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	@GetMapping("/findByCedulaNotClientes/{cedula}")
	public ResponseEntity<?> findByCedulaNotClientes(@PathVariable String cedula) {
		try {
			PersonaDto persona = personaService.findByCedulaNotClientes(cedula);
			return new ResponseEntity<>(persona, HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DataAccessException("Error en la consulta de la persona en el servidor", e) {
			};
		}
	}

}
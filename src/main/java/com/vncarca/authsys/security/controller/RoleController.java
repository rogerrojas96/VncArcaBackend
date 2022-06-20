package com.vncarca.authsys.security.controller;

import java.util.List;
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

import com.vncarca.arcasys.responses.CustomResponseEntity;
import com.vncarca.authsys.security.model.Rol;
import com.vncarca.authsys.security.service.RoleService;

import io.swagger.annotations.Api;

@Api(tags = "Roles", description = "Controlador para CRUD de Roles")
@RequestMapping("/roles")
@RestController
public class RoleController {

	@Autowired
	RoleService roleService;

	@ResponseBody
	@GetMapping("/page")
	public Page<Rol> getRols(@RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Rol> pageRols = roleService.findAll(pageable);
		return pageRols;
	}

	@GetMapping("/")
	public List<Rol> getRoles() {
		return roleService.findAll();
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody()
	public ResponseEntity<Object> create(@Valid @RequestBody Rol rol) {
		try {
			Rol newRol = roleService.save(rol);
			return new CustomResponseEntity(HttpStatus.CREATED, "Rol guardado con exito", newRol).response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al guardar Rol en el servidor", e) {
			};
		}
	}

	@ResponseBody
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Rol rol, @PathVariable Long id) {
		Rol rolToUpdate = roleService.findById(id);
		if (!Objects.equals(id, rol.getId())) {
			throw new IllegalArgumentException("El id {" + id + "} no coincide con el id del rol");
		}
		try {
			rolToUpdate = rol;
			Rol rolUpdate = roleService.save(rolToUpdate);
			return new CustomResponseEntity(HttpStatus.CREATED, "Rol actualizado con éxito", rolUpdate).response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al actualizar el Rol en el servidor", e) {
			};
		}
	}

	@ResponseBody
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			roleService.delete(id);
			return new CustomResponseEntity(HttpStatus.OK, "Rol eliminado con exito").response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al eliminar el Rol en el servidor", e) {
			};
		}
	}

	@ResponseBody
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		try {
			Rol rol = roleService.findById(id);
			return new ResponseEntity<Rol>(rol, HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DataAccessException("Error en la consulta del Rol en el servidor", e) {
			};
		}
	}
}

package com.vncarca.arcasys.usuario.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vncarca.arcasys.responses.CustomResponseEntity;
import com.vncarca.arcasys.usuario.model.UserDto;
import com.vncarca.arcasys.usuario.services.UserService;
import com.vncarca.authsys.security.model.Usuario;

import io.swagger.annotations.Api;

@Api(tags = "Usuarios", description = "Controlador para CRUD de usuarios")
@RestController
@RequestMapping("/usuarios")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder encoder;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/page")
	public Page<Usuario> getUsers(@RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer size,
			@RequestParam(required = false, defaultValue = "", name = "username") String username) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Usuario> pageUsuarios = null;

		if (username.isEmpty() || username == null) {
			pageUsuarios = userService.findAll(pageable);
		} else {
			pageUsuarios = userService.findByusername(pageable, username.toUpperCase());
		}
		return pageUsuarios;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/")
	public List<Usuario> retireveUsers() {
		return userService.findAll();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		Usuario newUsuario = null;
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			usuario.setPassword(encoder.encode(usuario.getPassword()));
			newUsuario = userService.save(usuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al guardar usuario en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Usuario guardado con exito");
		response.put("usuario", newUsuario);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();

		try {
			usuario = userService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en la consulta de usuario en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (usuario == null) {
			response.put("mensaje", "El usuario con ID: ".concat(id.toString()).concat(" no existe en el servidor"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {
		Usuario user = userService.findById(id);

		Usuario usuarioUpdate = null;

		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (user == null) {
			response.put("mensaje", "Error al actualizar, el usuario con ID: ".concat(id.toString())
					.concat(" no existe en el servidor"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			user.setUsername(usuario.getUsername());
			user.setPersona(usuario.getPersona());
			user.setRoles(usuario.getRoles());

			usuarioUpdate = userService.save(user);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en el servidor");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Usuario actualizado con exito");
		response.put("usuario", usuarioUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			userService.delete(id);
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al eliminar el usuario", e) {
			};
		}

		return new ResponseEntity<>("Usuario eliminado con exito", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PatchMapping("/{id}")
	public ResponseEntity<?> disable(@PathVariable Long id) {
		try {
			userService.changeStatus(id);
			return new CustomResponseEntity(HttpStatus.OK, "Usuario actualizado con exito").response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al actualizar estado del usuario", e) {
			};
		}

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/validatePassword")
	public Boolean passwordCorrecta(@RequestParam(required = true) Long id,
			@RequestParam(required = true) String currentPassword) {
		try {
			Usuario user = userService.findById(id);
			return encoder.matches(currentPassword, user.getPassword());
		} catch (DataAccessException e) {
			throw new DataAccessException("Ocurrió un error", e) {
			};
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PatchMapping("/changepasswd/{id}")
	public ResponseEntity<?> patchPassword(@PathVariable Long id, @RequestParam(required = true) String passwd) {
		try {
			userService.patchPassword(encoder.encode(passwd), id);
			return new CustomResponseEntity(HttpStatus.OK, "Contraseña actualizada con exito").response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al actualizar la contraseña", e) {
			};
		}
	}

	// @PreAuthorize("#username == authentication.principal.username")
	@GetMapping("/pofile")
	public ResponseEntity<UserDto> myProfile() {
		return ResponseEntity.ok(userService.getUserProfile());
	}
}

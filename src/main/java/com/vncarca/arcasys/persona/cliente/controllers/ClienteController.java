package com.vncarca.arcasys.persona.cliente.controllers;

import com.vncarca.arcasys.persona.cliente.ClienteDto;
import com.vncarca.arcasys.persona.cliente.ClienteDtoExtends;
import com.vncarca.arcasys.persona.cliente.Services.ClienteService;
import com.vncarca.arcasys.responses.CustomResponseEntity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Api(tags = "Clientes", description = "Controlador para CRUD de clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	ClienteService clienteService;

	@ResponseBody
	@GetMapping("/page")
	public Page<ClienteDtoExtends> getClientes(@RequestParam(required = true) Integer page,
	                                           @RequestParam(required = true) Integer size, @RequestParam(required = false, defaultValue = "") String cedula) {
		Pageable pageable = PageRequest.of(page, size);
		Page<ClienteDtoExtends> pageClientes = null;
		if (cedula.isEmpty() || cedula == null) {
			pageClientes = clienteService.findAll(pageable);
		} else {
			pageClientes = clienteService.findByCedula(pageable, cedula);
		}
		return pageClientes;
	}

	@GetMapping("/")
	public List<ClienteDtoExtends> getClientes() {
		return clienteService.findAll();
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody ClienteDto cliente) {
		try {
			ClienteDtoExtends newCliente = clienteService.save(cliente);
			return new CustomResponseEntity(HttpStatus.CREATED, "Cliente guardado con exito", newCliente).response();
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("El cliente ya se encuentra registrado", e) {
			};
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al guardar cliente en el servidor", e) {
			};
		}
	}

	@ResponseBody
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody ClienteDtoExtends cliente,
	                                @PathVariable Long id) {
		ClienteDtoExtends clienteToUpdate = clienteService.findById(id);
		if (!Objects.equals(id, cliente.getId())) {
			throw new IllegalArgumentException("El id {" + id + "} no coincide con el id del cliente");
		}
		try {
			clienteToUpdate = cliente;

			ClienteDtoExtends clienteUpdate = clienteService.update(clienteToUpdate);
			return new CustomResponseEntity(HttpStatus.CREATED, "Cliente actualizado con exito", clienteUpdate).response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al actualizar la cliente en el servidor", e) {
			};
		}
	}

	@ResponseBody
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			clienteService.delete(id);
			return new CustomResponseEntity(HttpStatus.OK, "Cliente eliminado con exito").response();
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException("¡No existe Ciente con id: " + id.toString() + " !",
					e.getExpectedSize());
		}
	}

	@ResponseBody
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		try {
			ClienteDtoExtends cliente = clienteService.findById(id);
			return new ResponseEntity<ClienteDtoExtends>(cliente, HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DataAccessException("Error en la consulta del cliente en el servidor", e) {
			};
		}
	}

	@ResponseBody
	@GetMapping("/find")
	public ResponseEntity<?> getByCedula(@RequestParam(required = true) String cedula) {
		try {
			return new ResponseEntity<ClienteDtoExtends>(clienteService.findByCedula(cedula), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DataAccessException("Error en la consulta del cliente en el servidor", e) {
			};
		}
	}
}
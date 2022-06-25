package com.vncarca.arcasys.animal.controllers;

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

import com.vncarca.arcasys.animal.model.Animal;
import com.vncarca.arcasys.animal.services.AnimalService;
import com.vncarca.arcasys.responses.CustomResponseEntity;

import io.swagger.annotations.Api;

@Api(tags = "Animales", description = "Controlador para CRUD de animales")
@RestController
@RequestMapping("/animales")
public class AnimalController {
	@Autowired
	AnimalService animalService;

	@ResponseBody
	@GetMapping("/page")
	public Page<Animal> getAnimals(@RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Animal> pageAnimals = animalService.findAll(pageable);
		return pageAnimals;
	}

	@GetMapping("/")
	public List<Animal> getAnimales() {
		return animalService.findAll();
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody()
	public ResponseEntity<Object> create(@Valid @RequestBody Animal animal) {
		try {
			Animal newAnimal = animalService.save(animal);
			return new CustomResponseEntity(HttpStatus.CREATED, "Animal guardado con exito", newAnimal).response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al guardar Animal en el servidor", e) {
			};
		}
	}

	@ResponseBody
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Animal animal, @PathVariable Long id) {
		Animal animalToUpdate = animalService.findById(id);
		if (!Objects.equals(id, animal.getId())) {
			throw new IllegalArgumentException("El id {" + id + "} no coincide con el id del animal");
		}
		try {
			animalToUpdate = animal;
			Animal animalUpdate = animalService.save(animalToUpdate);
			return new CustomResponseEntity(HttpStatus.CREATED, "Animal actualizada ani exito", animalUpdate).response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al actualizar el Animal en el servidor", e) {
			};
		}
	}

	@ResponseBody
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			animalService.delete(id);
			return new CustomResponseEntity(HttpStatus.OK, "Animal eliminado con exito").response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al eliminar el Animal en el servidor", e) {
			};
		}
	}

	@ResponseBody
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		try {
			Animal animal = animalService.findById(id);
			return new ResponseEntity<Animal>(animal, HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DataAccessException("Error en la consulta del Animal en el servidor", e) {
			};
		}
	}

}

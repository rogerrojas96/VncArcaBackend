package com.vncarca.arcasys.animal.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.vncarca.arcasys.animal.model.Animal;
import com.vncarca.arcasys.animal.services.AnimalService;

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

@Api(tags = "Animales", description = "Controlador para CRUD de animales")
@RestController
@RequestMapping("/animales")
public class AnimalController {
    @Autowired
    AnimalService animalService;

    // EndPoint listar animales
    @ResponseBody
    @GetMapping("/page")
    public Page<Animal> getAnimals(@RequestParam(required = true) Integer page,
            @RequestParam(required = true) Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Animal> pageAnimals = animalService.findAll(pageable);
        return pageAnimals;
    }
    @GetMapping("/")
	public List<Animal> getAnimales(){
		 return animalService.findAll();
	}

    // EndPoint registrar Animal
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Animal animal, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Animal newAnimal = null;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            newAnimal = animalService.save(animal);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar Animal en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Animal guardada ani exito");
        response.put("animal", newAnimal);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // EndPoint Actualizar Animal
    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Animal animal, BindingResult result,
            @PathVariable Long id) {
        Animal ani = animalService.findById(id);

        Animal animalUpdate = null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (ani == null) {
            response.put("mensaje", "Error al actualizar, el Animal ani ID: ".concat(id.toString())
                    .concat(" no existe en el servidor"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            ani = animal;

            animalUpdate = animalService.save(ani);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el Animal en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Animal actualizada ani exito");
        response.put("animal", animalUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // EndPoint Eliminar Animal
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            animalService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el Animal en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Animal eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    // EndPoint Buscar por ID
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Animal Animal = null;
        Map<String, Object> response = new HashMap<>();

        try {
            Animal = animalService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error en la consulta de Animal en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (Animal == null) {
            response.put("mensaje", "El Animal con ID: ".concat(id.toString()).concat(" no existe en el servidor"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Animal>(Animal, HttpStatus.OK);
    }

}

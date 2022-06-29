package com.vncarca.arcasys.animal.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vncarca.arcasys.animal.dto.AnimalRefugioRequest;
import com.vncarca.arcasys.animal.dto.AnimalRefugioResponse;
import com.vncarca.arcasys.animal.services.IAnimalRefugioService;
import com.vncarca.util.Response;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Api(tags = "Animales Refugio", description = "Controlador para CRUD para animales del Refugio")
@RestController
@RequestMapping("/animalesrefugio")
public class AnimalRefugioController {
	
	@Autowired
	IAnimalRefugioService animalService;

	
	private Map<String, Object> response = new HashMap<>();
	private HttpStatus status; 


	@ResponseBody
	@GetMapping("/all")
	public ResponseEntity<?> getAllAnimales(@RequestParam(required = true)Integer page, @RequestParam(required = true)Integer size) {
		response.clear();
		Pageable pageable = PageRequest.of(page, size);
		response.put("animales", animalService.getAllAnimales(pageable));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@ResponseBody
	@GetMapping("/all/noadoptados")
	public ResponseEntity<?> getAnimalesNoAdoptados(@RequestParam(required = true)Integer page, @RequestParam(required = true)Integer size) {
		response.clear();
		Pageable pageable = PageRequest.of(page, size);
		response.put("animales", animalService.getAnimalesNoAdoptados(pageable));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@ResponseBody
	@PostMapping("/")
	public ResponseEntity<?> guardarAnimal(@RequestPart(required = true,name = "multipartFile") MultipartFile multipartFile, @Valid @RequestBody AnimalRefugioRequest request, BindingResult result){
		response.clear();
		if(!result.hasErrors()){
			try{
				Response<AnimalRefugioResponse> animal = animalService.guardarAnimal(request, multipartFile);
				if(animal.getStatus() == HttpStatus.CREATED){
					response.put("mensaje", "Animal guardado con exito!");
					response.put("animal", animal.getBody());
				}else if(animal.getStatus() == HttpStatus.BAD_REQUEST){
					response.put("mensaje", "El formato de la imagen no es correcto!");
				}else{
					response.put("mensaje", "Ocurrio un error al intental subir la imagen al servidor!");
				}
				status = animal.getStatus();
			}catch(DataAccessException e){
				response.put("mensaje", "Ha ocurrido un error en el servidor al intentar guardar un animal!");
				response.put("error", e.getMostSpecificCause().getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else{
			response.put("errors", getErrors(result));
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(response, status);
	}


	@ResponseBody
	@PutMapping("/{idAnimal}")
	public ResponseEntity<?>  actualizarAnimal(@Valid @RequestBody AnimalRefugioRequest request, BindingResult result, 
			@RequestParam(required = true) MultipartFile multipartFile, @PathVariable Long idAnimal) {
		response.clear();
		if(!result.hasErrors()){
			try{
				Response<AnimalRefugioResponse> animal = animalService.actualizarAnimal(request, multipartFile, idAnimal);
				if(animal.getStatus() == HttpStatus.OK){
					response.put("mensaje", "Animal actualizado con exito!");
					response.put("animal", animal.getBody());
				}else if(animal.getStatus() == HttpStatus.BAD_REQUEST){
					response.put("mensaje", "El formato de la imagen no es correcto!");
				}else if(animal.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR){
					response.put("mensaje", "Ocurrio un error al intental subir la imagen al servidor!");
				}else{
					response.put("mensaje", "No existe el animal con id: "+idAnimal.toString()+"!");
				}
				status = animal.getStatus();
			}catch(DataAccessException e){
				response.put("mensaje", "Ha ocurrido un error en el servidor al intentar actualizar el animal con id: "+idAnimal.toString()+"!");
				response.put("error", e.getMostSpecificCause().getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else{
			response.put("errors", getErrors(result));
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(response, status);
	}

	@ResponseBody
	@GetMapping("/{idAnimal}")
	public ResponseEntity<?> getAnimalPorId(@PathVariable Long idAnimal) {
		response.clear();
		AnimalRefugioResponse animal = animalService.getAnimalPorId(idAnimal);
		if(animal != null){
			response.put("mensaje", "OK!");
			response.put("animal", animal);
			status = HttpStatus.OK;
		}else{
			response.put("mensaje", "No existe el animal con id: "+idAnimal.toString()+"!");
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(response, status);
	}


	@ResponseBody
	@DeleteMapping("/{idAnimal}")
	public ResponseEntity<?> eliminarAnimal(Long idAnimal) {
		response.clear();
		Response<AnimalRefugioResponse> animal = animalService.eliminarAnimal(idAnimal);
		if(animal.getStatus() == HttpStatus.OK){
			response.put("mensaje", "animal eliminado con exito!");
			response.put("animal", animal.getBody());
		}else if(animal.getStatus() == HttpStatus.NOT_FOUND){
			response.put("mensaje", "No existe el animal con id: "+idAnimal.toString()+"!");
		}else{
			response.put("mensaje", "Ocurrio un error al intental eliminar la imagen del servidor!");
		}
		status = animal.getStatus();
		return new ResponseEntity<>(response, status);
	}


	/**
	 * MÉTODOS AUXILIARES ---------------------------------------------------------------------
	 */
	private List<String> getErrors(BindingResult result){
		return result.getFieldErrors().stream().map(
			err -> {
				return "El campo '"+err.getField()+"' "+err.getDefaultMessage();
			}
		).collect(Collectors.toList());
	}


	/*@ResponseBody
	@GetMapping("/page")
	public Page<AnimalRefugio> getAnimals(@RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<AnimalRefugio> pageAnimals = animalService.findAll(pageable);
		return pageAnimals;
	}

	@GetMapping("/")
	public List<AnimalRefugio> getAnimales() {
		return animalService.findAll();
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody()
	public ResponseEntity<Object> create(@Valid @RequestBody AnimalRefugio animal) {
		try {
			AnimalRefugio newAnimal = animalService.save(animal);
			return new CustomResponseEntity(HttpStatus.CREATED, "Animal guardado con exito", newAnimal).response();
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al guardar Animal en el servidor", e) {
			};
		}
	}

	@ResponseBody
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody AnimalRefugio animal, @PathVariable Long id) {
		AnimalRefugio animalToUpdate = animalService.findById(id);
		if (!Objects.equals(id, animal.getId())) {
			throw new IllegalArgumentException("El id {" + id + "} no coincide con el id del animal");
		}
		try {
			animalToUpdate = animal;
			AnimalRefugio animalUpdate = animalService.save(animalToUpdate);
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
			AnimalRefugio animal = animalService.findById(id);
			return new ResponseEntity<AnimalRefugio>(animal, HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DataAccessException("Error en la consulta del Animal en el servidor", e) {
			};
		}
	}*/

}

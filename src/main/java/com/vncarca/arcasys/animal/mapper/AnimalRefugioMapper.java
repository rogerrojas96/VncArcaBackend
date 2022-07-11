package com.vncarca.arcasys.animal.mapper;

import com.vncarca.arcasys.animal.dto.AnimalRefugioRequest;
import com.vncarca.arcasys.animal.dto.AnimalRefugioResponse;
import com.vncarca.arcasys.animal.model.AnimalRefugio;

public class AnimalRefugioMapper {

	public static AnimalRefugio toAnimal(AnimalRefugioRequest animalRequest) {
		AnimalRefugio animal = new AnimalRefugio();
		animal.setColorCaracteristicas(animalRequest.getColorCaracteristicas());
		animal.setEdad(animalRequest.getEdad());
		animal.setEspecie(animalRequest.getEspecie());
		animal.setFechaNacimiento(animalRequest.getFechaNacimiento());
		animal.setLugarEstancia(animalRequest.getLugarEstancia());
		animal.setNombre(animalRequest.getNombre());
		animal.setObservacionesProcedencia(animalRequest.getObservacionesProcedencia());
		animal.setPeso(animalRequest.getPeso());
		animal.setAdoptado(animalRequest.getAdoptado());
		animal.setSexo(animalRequest.getSexo());
		animal.setRaza(animalRequest.getRaza());
		animal.setProcedencia(animalRequest.getProcedencia());
		return animal;
	}

	public static AnimalRefugioResponse toResponse(AnimalRefugio animal) {
		AnimalRefugioResponse response = new AnimalRefugioResponse();
		response.setAdoptado(animal.getAdoptado());
		response.setColorCaracteristicas(animal.getColorCaracteristicas());
		response.setEdad(animal.getEdad());
		response.setEspecie(animal.getEspecie());
		response.setFechaNacimiento(animal.getFechaNacimiento());
		response.setId(animal.getId());
		response.setLugarEstancia(animal.getLugarEstancia());
		response.setNombre(animal.getNombre());
		response.setObservacionesProcedencia(animal.getObservacionesProcedencia());
		response.setPeso(animal.getPeso());
		response.setProcedencia(animal.getProcedencia());
		response.setRaza(animal.getRaza());
		response.setSexo(animal.getSexo());
		response.setUrlImagenAnimal(animal.getUrlImagenAnimalCld());
		return response;
	}
}

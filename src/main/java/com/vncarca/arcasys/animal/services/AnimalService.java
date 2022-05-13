package com.vncarca.arcasys.animal.services;

import com.vncarca.arcasys.animal.model.Animal;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface AnimalService {
	public Page<Animal> findAll(Pageable pageable);
	// METODO PARA GUARDAR LOS DATOS DE UN Animal
	public Animal save(Animal animal);
	// METODO PARA OBTENER LOS DATOS DE UN Animal POR ID
	public Animal findById(Long id);
	// METODO PARA ELIMINAR UN Animal
	public void delete(Long id);
}

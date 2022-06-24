package com.vncarca.arcasys.animal.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vncarca.arcasys.animal.model.Animal;
import com.vncarca.arcasys.animal.model.AnimalDTO;
import com.vncarca.arcasys.animal.repository.AnimalRepository;

@Service
public class AnimalServiceImp implements AnimalService {

	@Autowired
	private AnimalRepository animalRepository;

	@Override
	public Page<Animal> findAll(Pageable pageable) {
		return animalRepository.findAll(pageable);
	}

	@Override
	public Animal save(Animal animal) {
		return animalRepository.save(animal);
	}

	@Override
	public Animal findById(Long id) {
		return animalRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
				"Animal con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public void delete(Long id) {
		animalRepository.deleteById(id);
	}

	@Override
	public List<Animal> findAll() {

		return animalRepository.findAll();
	}

	@Override
	public AnimalDTO getinfoDTO(Animal a) {
		AnimalDTO adto = new AnimalDTO();
		adto.setId(a.getId());
		adto.setFoto(a.getFoto());
		adto.setNombre(a.getNombre());
		return adto;
	}
}

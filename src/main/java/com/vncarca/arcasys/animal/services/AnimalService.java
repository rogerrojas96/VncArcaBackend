package com.vncarca.arcasys.animal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.animal.model.Animal;
import com.vncarca.arcasys.animal.model.AnimalDTO;

public interface AnimalService {
	public Page<Animal> findAll(Pageable pageable);

	public Animal save(Animal animal);

	public List<Animal> findAll();

	public Animal findById(Long id);

	public void delete(Long id);

	public AnimalDTO getinfoDTO(Animal a);
}

package com.vncarca.arcasys.animal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.animal.model.Animal;
import com.vncarca.arcasys.animal.model.AnimalDTO;
import com.vncarca.arcasys.globalService.GlovalService;

public interface AnimalService extends GlovalService<AnimalDTO, Animal> {
	Page<Animal> findAll(Pageable pageable);

	Animal save(Animal animal);

	List<Animal> findAll();

	Animal findById(Long id);

	void delete(Long id);

	AnimalDTO getinfoDTO(Animal a);
}

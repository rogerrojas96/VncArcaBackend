package com.vncarca.arcasys.animal.services;

import java.util.List;

import com.vncarca.arcasys.animal.model.Animal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnimalService {
	public Page<Animal> findAll(Pageable pageable);
	public Animal save(Animal animal);
	public List<Animal> findAll();
	public Animal findById(Long id);
	public void delete(Long id);
}

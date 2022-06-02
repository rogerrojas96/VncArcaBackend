package com.vncarca.arcasys.veterinario.services;

import java.util.List;

import com.vncarca.arcasys.veterinario.model.Veterinario;
import com.vncarca.arcasys.veterinario.repository.VeterinarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioServiceImp implements VeterinarioService {
	@Autowired
	VeterinarioRepository veterinarioRepository;

	@Override
	public Page<Veterinario> findAll(Pageable pageable) {

		return veterinarioRepository.findAll(pageable);
	}

	@Override
	public List<Veterinario> findAll() {

		return veterinarioRepository.findAll();
	}

	@Override
	public Veterinario save(Veterinario Veterinario) {

		return veterinarioRepository.save(Veterinario);
	}

	@Override
	public Veterinario findById(Long id) {

		return veterinarioRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {

		veterinarioRepository.deleteById(id);
	}

}

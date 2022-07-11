package com.vncarca.authsys.security.service;

import com.vncarca.authsys.security.model.Rol;
import com.vncarca.authsys.security.model.RolDto;
import com.vncarca.authsys.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class RoleServicesImp implements RoleService {

	@Autowired
	RolRepository rolRepository;

	@Override
	public RolDto save(RolDto role) {
		return convertToDto(rolRepository.save(convertToEntity(role)));
	}

	@Override
	public List<RolDto> findAll() {
		List<RolDto> roles = new ArrayList<>();
		rolRepository.findAll().stream().map(this::convertToDto).iterator().forEachRemaining(roles::add);
		return roles;
	}

	@Override
	public RolDto findById(Long id) {
		return rolRepository.findById(id).map(this::convertToDto).orElseThrow(() -> new NoSuchElementException(
				"Rol con ID: " + id.toString() + " no existe en el servidor"));
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		if (Objects.equals(findById(id).getId(),id))
			rolRepository.deleteById(id);
	}

	@Override
	public Page<RolDto> findAll(Pageable pageable) {
		return rolRepository.findAll(pageable).map(this::convertToDto);
	}

	@Override
	public RolDto convertToDto(Rol rol) {
		return new RolDto(rol.getId(),rol.getNombre());
	}

	@Override
	public Rol convertToEntity(RolDto rolDto) {
		return new Rol(rolDto.getId(),rolDto.getNombre());
	}
}

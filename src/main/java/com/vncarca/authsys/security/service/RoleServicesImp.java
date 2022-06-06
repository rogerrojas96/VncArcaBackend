package com.vncarca.authsys.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vncarca.authsys.security.model.Rol;
import com.vncarca.authsys.security.repository.RolRepository;

@Service
public class RoleServicesImp implements RoleService {

	@Autowired
	RolRepository rolRepository;

	@Override
	public Rol save(Rol role) {
		return rolRepository.save(role);
	}

	@Override
	public List<Rol> findAll() {
		List<Rol> roles = new ArrayList<>();
		rolRepository.findAll().iterator().forEachRemaining(roles::add);
		return roles;
	}

	@Override
	public Rol findById(Long id) {
		return rolRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		rolRepository.deleteById(id);
	}
}

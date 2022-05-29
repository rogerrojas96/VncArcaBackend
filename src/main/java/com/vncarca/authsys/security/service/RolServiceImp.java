package com.vncarca.authsys.security.service;

import com.vncarca.authsys.security.model.Rol;
import com.vncarca.authsys.security.repository.RolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImp implements RolService {
	@Autowired
	RolRepository rolRepository;
	@Override
	public Rol findByNombre(String nombre) {

		return rolRepository.findRoleByNombre(nombre);
	}

}

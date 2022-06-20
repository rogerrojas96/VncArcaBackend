package com.vncarca.authsys.security.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.authsys.security.model.Rol;

public interface RoleService {
	public Rol save(Rol role);

	public List<Rol> findAll();

	public Page<Rol> findAll(Pageable pageable);

	public Rol findById(Long id);

	public void delete(Long id);
}

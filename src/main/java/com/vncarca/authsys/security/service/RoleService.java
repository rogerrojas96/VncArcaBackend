package com.vncarca.authsys.security.service;

import java.util.List;

import com.vncarca.authsys.security.model.Rol;

public interface RoleService {
	public Rol save(Rol role);
	public List<Rol> findAll();
	public Rol findById(Long id);
	public void delete(Long id);
}

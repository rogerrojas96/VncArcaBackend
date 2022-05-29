package com.vncarca.authsys.security.service;

import com.vncarca.authsys.security.model.Rol;

public interface RolService {
	Rol findByNombre(String nombre);
}

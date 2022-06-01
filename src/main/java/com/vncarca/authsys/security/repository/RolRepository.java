package com.vncarca.authsys.security.repository;

import com.vncarca.authsys.security.model.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
	Rol findRoleByNombre(String nombre);
}

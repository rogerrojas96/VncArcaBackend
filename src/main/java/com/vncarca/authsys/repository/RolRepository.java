package com.vncarca.authsys.repository;

import java.util.Optional;

import com.vncarca.authsys.model.ERol;
import com.vncarca.authsys.model.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    
    Optional<Rol> findByNombre(ERol nombre);
}

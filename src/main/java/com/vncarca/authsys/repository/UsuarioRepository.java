package com.vncarca.authsys.repository;

import java.util.Optional;

import com.vncarca.authsys.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);
}

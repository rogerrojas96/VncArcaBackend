package com.vncarca.authsys.security.repository;

import com.vncarca.authsys.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByusernameOrEmail(String username,String email);
    Boolean existsByEmail(String email);
    Usuario findByUsername(String username);
}

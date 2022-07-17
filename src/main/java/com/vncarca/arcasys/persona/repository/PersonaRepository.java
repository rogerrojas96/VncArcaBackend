package com.vncarca.arcasys.persona.repository;

import com.vncarca.arcasys.persona.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
	boolean existsByCedula(String cedula);

	Optional<Persona> findByCedula(String cedula);

	@Query("SELECT p FROM Persona p where p.cedula=?1 and p.id not in (SELECT c.id FROM Cliente c )")
	Optional<Persona> findByCedulaNotClientes(String cedula);

	Page<Persona> findByCedula(Pageable pageable, String cedula);

}
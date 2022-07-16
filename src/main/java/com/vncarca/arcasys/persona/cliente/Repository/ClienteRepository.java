package com.vncarca.arcasys.persona.cliente.Repository;

import com.vncarca.arcasys.persona.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	public boolean existsByCedula(String cedula);

	public Optional<Cliente> findByCedula(String cedula);

	public Page<Cliente> findByCedula(Pageable pageable, String cedula);
}
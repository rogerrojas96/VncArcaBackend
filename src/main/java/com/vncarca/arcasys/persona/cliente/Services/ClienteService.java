package com.vncarca.arcasys.persona.cliente.Services;

import com.vncarca.arcasys.persona.cliente.ClienteDto;
import com.vncarca.arcasys.persona.cliente.ClienteDtoExtends;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {
	Page<ClienteDtoExtends> findAll(Pageable pageable);

	Page<ClienteDtoExtends> findByCedula(Pageable pageable, String cedula);

	List<ClienteDtoExtends> findAll();

	ClienteDtoExtends save(ClienteDto clienteDto);

	ClienteDtoExtends update(ClienteDtoExtends clienteDto);

	ClienteDtoExtends findById(Long id);

	ClienteDtoExtends findByCedula(String cedula);

	void delete(Long id);
}
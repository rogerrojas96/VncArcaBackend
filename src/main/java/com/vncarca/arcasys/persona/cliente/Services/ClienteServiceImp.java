package com.vncarca.arcasys.persona.cliente.Services;

import com.vncarca.arcasys.persona.cliente.Cliente;
import com.vncarca.arcasys.persona.cliente.ClienteDto;
import com.vncarca.arcasys.persona.cliente.ClienteDtoExtends;
import com.vncarca.arcasys.persona.cliente.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImp implements ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public Page<ClienteDtoExtends> findAll(Pageable p) {
		return clienteRepository.findAll(p).map(this::convertToDto);
	}

	@Override
	public Page<ClienteDtoExtends> findByCedula(Pageable pageable, String cedula) {

		return clienteRepository.findByCedula(pageable, cedula).map(this::convertToDto);
	}

	@Override
	public List<ClienteDtoExtends> findAll() {
		return clienteRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public ClienteDtoExtends save(ClienteDto cdto) {

		return this.convertToDto(clienteRepository.save(convertDtoToCliente(cdto)));
	}

	@Override
	public ClienteDtoExtends update(ClienteDtoExtends c) {
		return this.convertToDto(clienteRepository.save(convertToEntity(c)));
	}

	@Override
	public ClienteDtoExtends findById(Long id) {
		return clienteRepository.findById(id).map(this::convertToDto).orElseThrow(() -> new NoSuchElementException(
				"Cliente con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public ClienteDtoExtends findByCedula(String cedula) {
		return clienteRepository.findByCedula(cedula).map(this::convertToDto).orElseThrow(() -> new NoSuchElementException(
				"Cliente con cedula: " + cedula + " no existe en el servidor"));
	}

	public ClienteDtoExtends convertToDto(Cliente c) {
		return new ClienteDtoExtends(c.getId(), c.getCedula(), c.getNombre(), c.getApellidos(), c.getDireccion(), c.getTelefono(), c.getCelular(), c.getCorreo());
	}


	public Cliente convertToEntity(ClienteDtoExtends c) {
		return new Cliente(c.getId(), c.getCedula(), c.getNombre(), c.getApellidos(), c.getDireccion(), c.getTelefono(), c.getCelular(), c.getCorreo());
	}

	public Cliente convertDtoToCliente(ClienteDto c) {
		return new Cliente(c.getCedula(), c.getNombre(), c.getApellidos(), c.getDireccion(), c.getTelefono(), c.getCelular(), c.getCorreo());
	}

}
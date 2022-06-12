package com.vncarca.arcasys.usuario.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.usuario.model.UserDto;
import com.vncarca.authsys.security.model.Usuario;

public interface UserService {
	public Usuario save(Usuario usuario);

	public Page<Usuario> findAll(Pageable pageable);

	public Page<Usuario> findByusername(Pageable pageable, String username);

	public List<Usuario> findAll();

	public Usuario findById(Long id);

	public void delete(Long id);

	public void changeStatus(Long id);

	public UserDto getUserProfile();

	public void patchPassword(String password, Long id);
}

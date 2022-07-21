package com.vncarca.arcasys.usuario.services;

import com.vncarca.arcasys.globalService.GlobalService;
import com.vncarca.arcasys.usuario.model.ProfileDto;
import com.vncarca.arcasys.usuario.model.UsuarioDtoExtends;
import com.vncarca.arcasys.usuario.model.UsuarioDtoResponse;
import com.vncarca.authsys.security.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService extends GlobalService<UsuarioDtoExtends, Usuario> {
	public UsuarioDtoExtends save(UsuarioDtoExtends usuario);

	public ProfileDto updateProfile(ProfileDto profileDto);

	public Page<UsuarioDtoResponse> findAll(Pageable pageable);

	public Page<UsuarioDtoResponse> findByusername(Pageable pageable, String username);

	public List<UsuarioDtoResponse> findAll();

	public UsuarioDtoResponse findById(Long id);

	public ProfileDto findMyProfyleById(Long id);

	public void delete(Long id);

	public void changeStatus(Long id);

	public ProfileDto getUserProfile();

	public void patchPassword(String password, Long id);
}
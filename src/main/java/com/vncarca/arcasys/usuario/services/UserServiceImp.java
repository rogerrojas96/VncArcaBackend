package com.vncarca.arcasys.usuario.services;

import com.vncarca.arcasys.persona.services.PersonaService;
import com.vncarca.arcasys.usuario.model.ProfileDto;
import com.vncarca.arcasys.usuario.model.UsuarioDtoExtends;
import com.vncarca.arcasys.usuario.model.UsuarioDtoResponse;
import com.vncarca.authsys.security.dto.CustomUserDetails;
import com.vncarca.authsys.security.model.Usuario;
import com.vncarca.authsys.security.repository.UserRepository;
import com.vncarca.authsys.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PersonaService personaService;
	@Autowired
	RoleService roleService;

	@Override
	public ProfileDto getUserProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Usuario user = userRepository
				.findByusername(customUserDetails.getUsername())
				.orElseThrow(() -> new IllegalArgumentException(
						"Usuario no registrado : " + customUserDetails.getUsername()));
		return convertToProfileDto(user);
	}

	@Override
	public ProfileDto updateProfile(ProfileDto profileDto) {
		personaService.save(profileDto.getPersona());
		userRepository.updateProfile(profileDto.getUsername(), profileDto.getId());
		return profileDto;
	}

	@Override
	public ProfileDto findMyProfyleById(Long id) {
		return userRepository.findById(id).map(this::convertToProfileDto).orElseThrow(() -> new NoSuchElementException(
				"Usuario con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public List<UsuarioDtoResponse> findAll() {
		List<UsuarioDtoResponse> list = new ArrayList<>();
		userRepository.findAll().stream().map(this::convertToResponse).iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public UsuarioDtoExtends save(UsuarioDtoExtends usuario) {
		return convertToDto(userRepository.save(convertToEntity(usuario)));
	}

	@Override
	public Page<UsuarioDtoResponse> findAll(Pageable pageable) {
		return userRepository.findAll(pageable).map(this::convertToResponse);
	}

	@Override
	public UsuarioDtoResponse findById(Long id) {
		return userRepository.findById(id).map(this::convertToResponse).orElseThrow(() -> new NoSuchElementException(
				"Usuario con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void changeStatus(Long id) {
		Usuario u = convertToEntity(findById(id));
		userRepository.setStatus(!u.getEnabled(), id);
	}

	@Override
	public Page<UsuarioDtoResponse> findByusername(Pageable pageable, String username) {
		return userRepository.findByusername(pageable, username).map(this::convertToResponse);
	}

	@Override
	public void patchPassword(String password, Long id) {
		userRepository.patchPassword(password, id);

	}

	@Override
	public UsuarioDtoExtends convertToDto(Usuario u) {
		return new UsuarioDtoExtends(u.getId(), u.getUsername(), u.getPassword(), personaService.convertToDto(u.getPersona()), u.getRoles().stream().map(roleService::convertToDto).collect(Collectors.toSet()));
	}

	public ProfileDto convertToProfileDto(Usuario u) {
		return new ProfileDto(u.getId(), u.getUsername(), personaService.convertToDto(u.getPersona()));
	}

	public UsuarioDtoResponse convertToResponse(Usuario u) {
		return new UsuarioDtoResponse(u.getId(), u.getUsername(), u.getPassword(), personaService.convertToDto(u.getPersona()), u.getRoles().stream().map(roleService::convertToDto).collect(Collectors.toSet()), u.getEnabled());
	}

	public ProfileDto convertToProfile(Usuario u) {
		return new ProfileDto(u.getId(), u.getUsername(), personaService.convertToDto(u.getPersona()));
	}

	@Override
	public Usuario convertToEntity(UsuarioDtoExtends u) {
		return new Usuario(u.getId(), u.getUsername(), u.getPassword(), personaService.convertToEntity(u.getPersona()),
				u.getRoles().stream().map(roleService::convertToEntity).collect(Collectors.toList()));
	}

	public Usuario convertProfileToEntity(ProfileDto p) {
		UsuarioDtoExtends currentProfile = findById(p.getId());

		return new Usuario(currentProfile.getId(), p.getUsername(), currentProfile.getPassword(), personaService.convertToEntity(personaService.save(p.getPersona())),
				currentProfile.getRoles().stream().map(roleService::convertToEntity).collect(Collectors.toList()));
	}

}
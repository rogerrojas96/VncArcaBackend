package com.vncarca.arcasys.usuario.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.model.PersonaDto;
import com.vncarca.arcasys.usuario.model.UserDto;
import com.vncarca.authsys.security.dto.CustomUserDetails;
import com.vncarca.authsys.security.model.Usuario;
import com.vncarca.authsys.security.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
    public UserDto getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Usuario user = userRepository
                .findByusername(customUserDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuario no registrado : " + customUserDetails.getUsername()));
        return ConvertUserDTO(user);
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Usuario save(Usuario usuario) {

        return userRepository.save(usuario);
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public Usuario findById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public Page<Usuario> findByusername(Pageable pageable, String username) {
        return userRepository.findByusername(pageable,username);
    }

    @Override
    public void patchPassword(String password, Long id) {
      userRepository.patchPassword(password,id);

    }

    private UserDto ConvertUserDTO(Usuario user){
    UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPersonaDto(ConvertPersonaDTO(user.getPersona()));
    return userDto;
    }

    private PersonaDto ConvertPersonaDTO(Persona persona){
    PersonaDto personaDto = new PersonaDto();
        personaDto.setApellidos(persona.getApellidos());
        personaDto.setCedula(persona.getCedula());
        personaDto.setCelular(persona.getCelular());
        personaDto.setCorreo(persona.getCorreo());
        personaDto.setDireccion(persona.getDireccion());
        personaDto.setNombre(persona.getNombre());
        personaDto.setTelefono(persona.getTelefono());
    return personaDto;
 }


}

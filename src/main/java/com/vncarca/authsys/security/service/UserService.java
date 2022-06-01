package com.vncarca.authsys.security.service;

import java.util.List;

import com.vncarca.authsys.security.dto.LoginRequest;
import com.vncarca.authsys.security.dto.UserDto;
import com.vncarca.authsys.security.model.Usuario;

import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> login(LoginRequest loginRequest)  throws Exception ;
    List<Usuario> findAll();
    Usuario findOne(String username);
    UserDto getUserProfile();
}

package com.vncarca.authsys.security.service;

import org.springframework.http.ResponseEntity;

import com.vncarca.authsys.security.dto.LoginRequest;

public interface AuthService {
    ResponseEntity<?> login(LoginRequest loginRequest)  throws Exception ;
}

package com.vncarca.authsys.security.service;

import com.vncarca.authsys.security.dto.LoginResponse;
import org.springframework.http.ResponseEntity;

import com.vncarca.authsys.security.dto.LoginRequest;

public interface AuthService {
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest)  throws Exception ;
}

package com.vncarca.authsys.security.controller;

import javax.validation.Valid;

import com.vncarca.authsys.security.dto.LoginRequest;
import com.vncarca.authsys.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest
            
    ) throws Exception{
        
        return userService.login(loginRequest );
    }

    

}

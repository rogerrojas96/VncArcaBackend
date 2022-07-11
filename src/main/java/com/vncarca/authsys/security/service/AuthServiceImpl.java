package com.vncarca.authsys.security.service;

import com.vncarca.arcasys.persona.services.PersonaService;
import com.vncarca.authsys.security.dto.CustomUserDetails;
import com.vncarca.authsys.security.dto.LoginRequest;
import com.vncarca.authsys.security.dto.LoginResponse;
import com.vncarca.authsys.security.dto.Token;
import com.vncarca.authsys.security.model.Usuario;
import com.vncarca.authsys.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    PersonaService personaService;

    @Override
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

        HttpHeaders responseHeaders = new HttpHeaders();
        Usuario usuario = userRepository
                .findByusername(loginRequest.getUsername()).orElseThrow(
                        () -> new IllegalArgumentException("Usuario no registrado"));

            Authentication authentication = authenticate(loginRequest.getUsername(), loginRequest.getPassword());

            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            List<String> authorities = customUserDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            Token newAccessToken = tokenProvider.generateAccessToken(usuario.getUsername(), authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok().headers(responseHeaders)
                    .body(new LoginResponse(usuario.getId(), usuario.getUsername(),newAccessToken,personaService.convertToDto(customUserDetails.getUsuario().getPersona())));
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("Lo sentimos, tu cuenta no está habilitada. Comunícate con un administrador de ARCA.", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Lo sentimos, las credenciales que estás usando no son válidas.", e);
        }
    }
}

package com.vncarca.authsys.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.vncarca.authsys.model.ERol;
import com.vncarca.authsys.model.Rol;
import com.vncarca.authsys.model.Usuario;
import com.vncarca.authsys.repository.RolRepository;
import com.vncarca.authsys.repository.UsuarioRepository;
import com.vncarca.authsys.security.jwt.JwtUtils;
import com.vncarca.authsys.security.service.UserDetailsImpl;
import com.vncarca.authsys.util.RequestIniciarSesion;
import com.vncarca.authsys.util.RequestRegistrarse;
import com.vncarca.authsys.util.ResponseMensaje;
import com.vncarca.authsys.util.ResponseUsuarioInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin ({"*"})
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/iniciarSesion")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody RequestIniciarSesion loginRequest) {
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(new ResponseUsuarioInfo(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    @PostMapping("/registrarse")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RequestRegistrarse signUpRequest) {
        if (usuarioRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new ResponseMensaje("Error: Este nombre de usuario ya está registrado!"));
        }
        if (usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new ResponseMensaje("Error: Correo electrónico ya está en uso!"));
        }
        
        Usuario usuario = new Usuario(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Rol> roles = new HashSet<>();

        if (strRoles == null) {
            Rol usuarioRol = rolRepository.findByNombre(ERol.ROLE_DEFAULT_USER)
                .orElseThrow(() -> new RuntimeException("Error: No se encuentra el rol."));
            roles.add(usuarioRol);
        } else {
            strRoles.forEach(rol -> {
                switch (rol) {
                case "admin":
                    Rol adminRol = rolRepository.findByNombre(ERol.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: No se encuentra el rol."));
                    roles.add(adminRol);
                    break;
                case "veterinario":
                    Rol veterinarioRol = rolRepository.findByNombre(ERol.ROLE_VETERINARIO)
                        .orElseThrow(() -> new RuntimeException("Error: No se encuentra el rol."));
                    roles.add(veterinarioRol);
                    break;
                default:
                    Rol defaultUserRol = rolRepository.findByNombre(ERol.ROLE_DEFAULT_USER)
                        .orElseThrow(() -> new RuntimeException("Error: No se encuentra el rol."));
                    roles.add(defaultUserRol);
                }
            });
        }
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(new ResponseMensaje("Usuario registrado exitosamente!"));
    }

    @PostMapping("/cerrarSesion")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new ResponseMensaje("Sesión cerrada!"));
    }
}

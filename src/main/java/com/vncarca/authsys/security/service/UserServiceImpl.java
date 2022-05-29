package com.vncarca.authsys.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.vncarca.authsys.security.dto.CustomUserDetails;
import com.vncarca.authsys.security.dto.LoginRequest;
import com.vncarca.authsys.security.dto.LoginResponse;
import com.vncarca.authsys.security.dto.Token;
import com.vncarca.authsys.security.dto.UserDto;
import com.vncarca.authsys.security.exceptions.GlobalExceptionHandler;
import com.vncarca.authsys.security.model.Usuario;
import com.vncarca.authsys.security.repository.UserRepository;
import com.vncarca.authsys.security.util.CookieUtil;

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

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CookieUtil cookieUtil;

    @Override
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        HttpHeaders responseHeaders = new HttpHeaders();
        Usuario usuario = userRepository
                .findByusernameOrEmail(loginRequest.getUsername(), loginRequest.getUsername()).orElseThrow(
                        () -> new IllegalArgumentException("Usuario no registrado"));
        try {

            Authentication authentication = authenticate(loginRequest.getUsername(), loginRequest.getPassword());

            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            List<String> authorities = customUserDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            Token newAccessToken = tokenProvider.generateAccessToken(usuario.getUsername(), authorities);

            addAccessTokenCookie(responseHeaders, newAccessToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok().headers(responseHeaders)
                    .body(new LoginResponse(usuario.getId(), usuario.getUsername(), newAccessToken.getTokenValue(),
                            authorities, customUserDetails.getUsuario().getPersona()));

        } catch (Exception e) {
            return null;
        }

    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User diseabled", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Lo sentimos, las credenciales que estás usando no son válidas.", e);
        }
    }

    @Override
    public UserDto getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        Usuario user = userRepository
                .findByusernameOrEmail(customUserDetails.getUsername(), customUserDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuario no registrado : " + customUserDetails.getUsername()));
        return user.toUserDto();
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return null;
    }

    @Override
    public Usuario findOne(String username) {
        return userRepository.findByUsername(username);
    }

    private void addAccessTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE,
                cookieUtil.createAccessTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }

}

// System.out.print(
// "Usuario ------------->" + (CustomUserDetails) ((Authentication)
// authentication).getPrincipal());
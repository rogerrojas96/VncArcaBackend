package com.vncarca.authsys.security.dto;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vncarca.arcasys.persona.model.Persona;

import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class LoginResponse {

    private Long id;
    private String username;
    private String token;
    private List<String> roles;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Persona persona;

}

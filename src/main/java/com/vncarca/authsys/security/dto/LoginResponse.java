package com.vncarca.authsys.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vncarca.arcasys.persona.model.Persona;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class LoginResponse {

	private Long id;
	private String username;
	private Token token;
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Persona persona;

}

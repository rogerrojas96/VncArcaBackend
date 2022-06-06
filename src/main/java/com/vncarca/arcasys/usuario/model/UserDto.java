package com.vncarca.arcasys.usuario.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.vncarca.arcasys.persona.model.PersonaDto;

import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private PersonaDto personaDto;
}

package com.vncarca.arcasys.persona.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class PersonaDto {
	private Long id;
	private String cedula;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String celular;
	private String correo;
}

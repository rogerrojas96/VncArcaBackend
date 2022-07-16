package com.vncarca.arcasys.persona.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class PersonaDtoRes implements Serializable {
	private final Long id;
	@Size(min = 10, max = 10)
	@NotBlank
	@NotNull
	private final String cedula;
	@NotBlank
	@NotNull
	private final String nombre;
	@NotBlank
	@NotNull
	private final String apellidos;
	@NotBlank
	@NotNull
	private final String direccion;
	@Size(min = 7, max = 9)
	@NotBlank
	@NotNull
	private final String telefono;
	@Size(min = 9, max = 10)
	@NotBlank
	private final String celular;
	@NotBlank
	@NotNull
	@Email
	private final String correo;
}
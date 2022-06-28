package com.vncarca.arcasys.voluntarios.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VoluntarioDto {

	@NotBlank
	private String cedula;

	@NotBlank
	private String nombre;

	@NotBlank
	private String apellidos;

	@NotBlank
	private String direccion;

    @Size(min = 7, max = 9)
	@NotBlank
	private String telefono;

    @Size(min = 9, max = 10)
	@NotBlank
	private String celular;

	@NotBlank
	@Email
    private String correo;

    @NotBlank
    private String actividad;

    @NotBlank
    private String tipo;
}

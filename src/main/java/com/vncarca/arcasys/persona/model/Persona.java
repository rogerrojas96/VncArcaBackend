package com.vncarca.arcasys.persona.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "personas")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Size(min = 10, max = 10)
	@NotBlank
	@NotNull
	@Column(nullable = false,length = 10, unique = true)
	private String cedula;

	@NotBlank
	@NotNull
	@Column(nullable = false)
	private String nombre;

	@NotBlank
	@NotNull
	@Column(nullable = false)
	private String apellidos;

	@NotBlank
	@NotNull
	@Column(nullable = false)
	private String direccion;


    @Size(min = 7, max = 9)
	@NotBlank
	@NotNull
	@Column(nullable = false,length = 9)
	private String telefono;

    @Size(min = 9, max = 10)
	@NotBlank
	@Column(nullable = false ,length = 10)
	private String celular;

	@NotBlank
	@NotNull
	@Email
    @Column(length = 100, unique = true, nullable = false)
    private String correo;
}

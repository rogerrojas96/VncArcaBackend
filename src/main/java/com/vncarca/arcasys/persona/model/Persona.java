package com.vncarca.arcasys.persona.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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

	@Column(nullable = false, unique = true)
	private String cedula;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellidos;

	@Column(nullable = false)
	private String direccion;

	@Column(nullable = false)
	private String telefono;

	@Column(nullable = false)
	private String celular;

	@Column(nullable = false)
	private String correo;
}

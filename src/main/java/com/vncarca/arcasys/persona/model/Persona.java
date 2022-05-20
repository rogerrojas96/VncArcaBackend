package com.vncarca.arcasys.persona.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vncarca.arcasys.adopciones.model.Adoptante;
import com.vncarca.authsys.model.Usuario;

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

	@Column(nullable = false)

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

	@OneToOne(mappedBy = "persona",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Usuario usuario; 

	@OneToOne(mappedBy = "persona",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Adoptante adoptante;
}

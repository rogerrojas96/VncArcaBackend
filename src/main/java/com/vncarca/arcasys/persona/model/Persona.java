package com.vncarca.arcasys.persona.model;

import com.vncarca.arcasys.adopciones.model.Adoptante;
import com.vncarca.arcasys.donaciones.model.Donacion;
import com.vncarca.arcasys.veterinario.model.Veterinario;
import com.vncarca.arcasys.voluntarios.model.Voluntario;
import com.vncarca.authsys.security.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
//@Inheritance
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "personas")
@Loader(namedQuery = "findPersonaById")
@NamedQuery(name = "findPersonaById", query = "SELECT p FROM Persona p WHERE p.id = ?1 AND p.deleted = false")
//@Where(clause = "deleted=false")
@FilterDef(name = "personasEnabled")

@Filter(name = "personasEnabled",
		condition = "{deletePerson}.deleted is false",
		deduceAliasInjectionPoints = false,
		aliases = {@SqlFragmentAlias(alias = "deletePerson", table = "personas")}
)
@SQLDelete(sql = "UPDATE personas SET deleted = true WHERE id=?", callable = true)
public class Persona implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false, length = 10, unique = true)
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
	@Column(length = 100, unique = false, nullable = false)
	private String correo;

	@NotNull
	@Column(nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean deleted = Boolean.FALSE;

	//Soft delete en cascada todas entidades donde sea dependiente una persona

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	private Voluntario voluntario;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	private Veterinario veterinario;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	private List<Usuario> usuarios = new ArrayList<>();

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios.addAll(usuarios);
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	private List<Donacion> donaciones = new ArrayList<>();

	public void setDonaciones(List<Donacion> donaciones) {
		this.donaciones.addAll(donaciones);
	}

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	private Adoptante adoptante;

	public Persona(Long id, String cedula, String nombre, String apellidos, String direccion, String telefono, String celular, String correo) {
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.celular = celular;
		this.correo = correo;
	}

	public Persona(String cedula, String nombre, String apellidos, String direccion, String telefono, String celular, String correo) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.celular = celular;
		this.correo = correo;
	}
}
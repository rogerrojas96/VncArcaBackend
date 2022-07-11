package com.vncarca.arcasys.persona.model;

import com.vncarca.arcasys.adopciones.model.Adoptante;
import com.vncarca.arcasys.donaciones.model.Donacion;
import com.vncarca.arcasys.veterinario.model.Veterinario;
import com.vncarca.arcasys.voluntarios.model.Voluntario;
import com.vncarca.authsys.security.model.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE personas SET deleted = true WHERE id=?")
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
    @Column(length = 100, unique = false, nullable = false)
    private String correo;

	@NotNull
	@Column(nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean deleted=Boolean.FALSE;

	//Soft delete en cascada todas entidades donde sea dependiente una persona

	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "persona")
	private Voluntario voluntario;

	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "persona")
	private Veterinario veterinario;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	private List<Usuario> usuarios;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	private List<Donacion> donaciones;

	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "persona")
	private Adoptante adoptante;
}

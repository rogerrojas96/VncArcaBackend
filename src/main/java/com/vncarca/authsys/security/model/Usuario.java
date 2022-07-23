package com.vncarca.authsys.security.model;

import com.vncarca.arcasys.persona.model.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE usuarios SET deleted = true WHERE id=?")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@NotNull
	@Column(length = 50, unique = true, nullable = false)
	private String username;

	@NotBlank
	@NotNull
	@Column(length = 200, nullable = false)
	private String password;

	@NotNull
	@Column(nullable = false, columnDefinition = "tinyint(1) default 1")
	private Boolean enabled=Boolean.TRUE;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "persona_id", nullable = false)
	private Persona persona;

	@NotNull
	@Column(nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean deleted = Boolean.FALSE;

	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", updatable = true, nullable = false), inverseJoinColumns = @JoinColumn(name = "rol_id", updatable = true, nullable = false), uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "rol_id"})})
	public List<Rol> roles = new ArrayList<>();

	public void setRoles(List<Rol> roles) {
		this.roles.addAll(roles);
	}

	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param persona
	 * @param roles
	 */
	public Usuario(Long id, String username, String password, Persona persona, List<Rol> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.persona = persona;
		this.roles = roles;
	}

	/**
	 * @param id
	 * @param username
	 * @param password
	 */
	public Usuario(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
}
package com.vncarca.arcasys.carnetVacunacion.vacuna.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE vacuna SET deleted = true WHERE id=?")
public class Vacuna implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotNull
	@NotBlank
	private String nombre;

	@Column(nullable = false)
	@NotNull
	@NotBlank
	private String tipo;

	@Column(nullable = false)
	@NotNull
	@NotBlank
	private String descripcion;

	@NotNull
	@Column(nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean deleted=Boolean.FALSE;

	/**
	 * @param id
	 * @param nombre
	 * @param tipo
	 * @param descripcion
	 */
	public Vacuna(Long id, String nombre, String tipo, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
}

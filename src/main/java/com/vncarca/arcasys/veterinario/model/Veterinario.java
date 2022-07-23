package com.vncarca.arcasys.veterinario.model;

import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.serviciosarca.model.Cita;
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
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "veterinarios")
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE veterinarios SET deleted = true WHERE id=?")
public class Veterinario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank
	@NotNull
	private String cargo;

	@NotNull
	@Column(nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean deleted=Boolean.FALSE;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "veterinarios_personas", joinColumns = {
			@JoinColumn(name = "veterinario_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "persona_id", referencedColumnName = "id") })
	private Persona persona;
	
	/**
	 * @param id
	 * @param cargo
	 * @param persona
	 */
	public Veterinario(Long id, String cargo, Persona persona) {
		this.id = id;
		this.cargo = cargo;
		this.persona = persona;
	}

	//Para soft delete
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "veterinario")
	private List<Cita> citas = new ArrayList<>();

	public void setCitas(List<Cita> citas) {
		this.citas.addAll(citas);
	}
}
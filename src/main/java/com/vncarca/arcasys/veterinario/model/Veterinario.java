package com.vncarca.arcasys.veterinario.model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.vncarca.arcasys.persona.model.Persona;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="veterinarios")
public class Veterinario implements Serializable {	
 	
	private static final long serialVersionUID=1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String cargo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "veterinarios_personas", joinColumns = {
			@JoinColumn(name = "veterinario_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "persona_id", referencedColumnName = "id") })
	private Persona persona;
	
}

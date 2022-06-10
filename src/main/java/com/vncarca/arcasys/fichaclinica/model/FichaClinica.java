package com.vncarca.arcasys.fichaclinica.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.animal.model.Animal;
import com.vncarca.arcasys.veterinario.model.Veterinario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fichasClinicas")

public class FichaClinica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fechaIngreso;

	@Column(nullable = false)
	private String motivoConsulta;

	@Column(nullable = false)
	private String hallazgos;

	@Column(nullable = false)
	private float temperatura;

	@Column(nullable = false)
	private String conjuntiva;

	@Column(nullable = false)
	private float frecuenciaCardiaca;

	@Column(nullable = false)
	private float frecuenciaRespiratoria;

	@Column(nullable = false)
	private String TRC;

	@Column(nullable = false)
	private String mucosas;

	@Column(nullable = false)
	private Boolean esterilizacion;

	@Column(nullable = false)
	private float alimentacion;

	@Column(nullable = false)
	private String pronostico;

	/*
	 * El usuario podrá clasificar a los animales rescatados (pacientes internos o
	 * externos)
	 */
	@Column(nullable = false)
	private String tipoPaciente;

	@Column(nullable = false)
	private String examenes_solicitados;

	@Column(nullable = false)
	private String diagnosticoDiferencial;

	@Column(nullable = false)
	private float costo;

	@NotNull
	@ManyToOne
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Veterinario veterinario;

	@NotNull
	@NotBlank
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "animal_id", nullable = false, insertable = false, updatable = false)
	private Animal animal;

}

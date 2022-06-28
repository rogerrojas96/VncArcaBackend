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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.animal.model.Animal;
import com.vncarca.arcasys.enums.Enum;
import com.vncarca.arcasys.enums.Types;
import com.vncarca.arcasys.enums.Types.ESTERILIZACION;
import com.vncarca.arcasys.enums.Types.TIPO_PACIENTE;
import com.vncarca.arcasys.veterinario.model.Veterinario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fichas_clinicas")

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

	@NotNull
	@Column(nullable = false)
	@Enum(enumClass = Types.ESTERILIZACION.class, regexp = "SI o NO")
	private String esterilizacion;

	@Column(nullable = false)
	private float alimentacion;

	@Column(nullable = false)
	private String pronostico;

	/*
	 * El usuario podrá clasificar a los animales rescatados (pacientes internos o
	 * externos)
	 */
	@Column(nullable = false)
	@Enum(enumClass = Types.TIPO_PACIENTE.class, regexp = "INTERNO o EXTERNO")
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
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "animal_id", nullable = false, insertable = true, updatable = true)
	private Animal animal;

	/**
	 * @param fechaIngreso
	 * @param motivoConsulta
	 * @param hallazgos
	 * @param temperatura
	 * @param conjuntiva
	 * @param frecuenciaCardiaca
	 * @param frecuenciaRespiratoria
	 * @param tRC
	 * @param mucosas
	 * @param esterilizacion
	 * @param alimentacion
	 * @param pronostico
	 * @param tipoPaciente
	 * @param examenes_solicitados
	 * @param diagnosticoDiferencial
	 * @param costo
	 * @param veterinario
	 * @param animal
	 */
	public FichaClinica(Date fechaIngreso, String motivoConsulta, String hallazgos, float temperatura, String conjuntiva,
			float frecuenciaCardiaca, float frecuenciaRespiratoria, String tRC, String mucosas,
			@NotNull @Enum(enumClass = ESTERILIZACION.class, regexp = "SI o NO") String esterilizacion, float alimentacion,
			String pronostico, @Enum(enumClass = TIPO_PACIENTE.class, regexp = "INTERNO o EXTERNO") String tipoPaciente,
			String examenes_solicitados, String diagnosticoDiferencial, float costo, @NotNull Veterinario veterinario,
			@NotNull Animal animal) {
		this.fechaIngreso = fechaIngreso;
		this.motivoConsulta = motivoConsulta;
		this.hallazgos = hallazgos;
		this.temperatura = temperatura;
		this.conjuntiva = conjuntiva;
		this.frecuenciaCardiaca = frecuenciaCardiaca;
		this.frecuenciaRespiratoria = frecuenciaRespiratoria;
		TRC = tRC;
		this.mucosas = mucosas;
		this.esterilizacion = esterilizacion;
		this.alimentacion = alimentacion;
		this.pronostico = pronostico;
		this.tipoPaciente = tipoPaciente;
		this.examenes_solicitados = examenes_solicitados;
		this.diagnosticoDiferencial = diagnosticoDiferencial;
		this.costo = costo;
		this.veterinario = veterinario;
		this.animal = animal;
	}

}
